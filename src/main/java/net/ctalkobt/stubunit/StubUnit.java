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

public class StubUnit {

    private static final IStubControl control = new StubControlImpl();

    private StubUnit() {
        /* No default constructor .. .*/
    }

    public static final IStubControl createControl() {
        return new StubControlImpl();
    }

    public static final <T> IStubControl addStub(T stub, Class<T> stubRef) {
        return control.addStub(stub, stubRef);
    }

    public static final <T> IStubControl delStub(T stub) {
        return control.delStub(stub);
    }

    public static final <T> IStubControl delStub(T stub, Class<T> stubRef) {
        return control.delStub(stub, stubRef);
    }

    public static final <T> IStubControl populate(T object) {
        return control.populate(object);
    }

    public static final IStubControl populateAll() {
        return control.populateAll();
    }

}
