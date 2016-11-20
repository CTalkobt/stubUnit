/*
 * Copyright (C) 2016 Craig.Taylor <ctalkobt@ctalkobt.net>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package net.ctalkobt.stubunit;

import java.beans.Transient;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/* package */ class StubControlImpl implements IStubControl {
    private final Map<Class, Object> stubMap = new HashMap<>();
    
    @Override
    public <T> IStubControl addStub(T stubRef, Class<T> stubClassRef) {
        stubMap.put(stubClassRef, stubRef);
        return this;
    }

    @Override
    public <T extends Object> IStubControl delStub(T stub) {
        stubMap.entrySet().stream()
            .filter(en -> en.getValue().equals(stub))
            .forEach(st -> remove(st));

        return this;
    }

    @Override
    public IStubControl populate(Object objects[]) {
        for (Object object:objects)
        {
            Arrays.asList(object.getClass().getDeclaredMethods()).parallelStream()
                .filter(m -> m.getName().startsWith("set") &&
                             m.getParameterCount() == 1 &&
                             m.getAnnotationsByType(Transient.class).length == 0 &&
                             stubMap.keySet().contains(m.getParameterTypes()[0]))
                .forEach(m ->  {
                    Class setterClass = m.getParameterTypes()[0];
                    Object val = stubMap.get(setterClass);
                    try {
                        m.invoke(object, val);
                    } catch (ReflectiveOperationException | IllegalArgumentException ex) {
                        /** TODO: Best way to handle invocation errors? */
                        Logger.getLogger(StubControlImpl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
        }
        return this;
    }

    @Override
    public IStubControl populateAll() {
        populate(stubMap.values().toArray());
        return this;
    }

    @Override
    public <T> IStubControl delStub(T stub, Class<T> stubRef) {
        stubMap.entrySet().stream()
            .filter(en -> en.getValue().equals(stub) &&
                          en.getKey().equals(stubRef))
            .forEach(st -> remove(st));
        return this;
    }

    public boolean remove(Entry<Class, Object> en) {
        return stubMap.remove(en.getKey(), en.getValue());
    }
    
}
