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

    private static final IStubControl CONTROL = new StubControlImpl();

    private StubUnit() {
        /* No default constructor .. .*/
    }

    /**
     * Create a new StubControl instance. 
     * 
     * @return 
     */
    public static final IStubControl createControl() {
        return new StubControlImpl();
    }

    /** 
     * Registers stub reference for a specific class. 
     * 
     * @param <T>
     * @param stubRef
     * @param stubClassRef
     * @return 
     */    
    public static final <T> IStubControl addStub(T stubRef, Class<T> stubClassRef) {
        return CONTROL.addStub(stubRef, stubClassRef);
    }

    
    /**
     * Removes a stub reference.
     * 
     * @param <T>
     * @param stubRef
     * @return 
     */    
    public static final <T> IStubControl delStub(T stubRef) {
        return CONTROL.delStub(stubRef);
    }

    /**
     * Removes a stub reference based upon stub and class type. 
     * 
     * @param <T>
     * @param stubRef
     * @param stubClassRef
     * @return 
     */    
    public static final <T> IStubControl delStub(T stubRef, Class<T> stubClassRef) {
        return CONTROL.delStub(stubRef, stubClassRef);
    }

    /**
     * Populate 1 or more objects recursively. Any occurrences of setSomeName(X)
     * where the type of X has previously been configured via {@link addStub}
     * will be populated.
     * 
     * @param objects
     * @return 
     */    
    public static final IStubControl populate(Object... objects) {
        return CONTROL.populate(objects);
    }

    /**
     * Populate all stub objects that have been registered.
     * 
     * @return 
     * @see #populate(java.lang.Object...) 
     */    
    public static final IStubControl populateAll() {
        return CONTROL.populateAll();
    }

}
