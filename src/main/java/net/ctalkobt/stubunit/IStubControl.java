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

/**
 * Control interface for {@link StubUnit}.  This allows more than one 
 * set of stubs to be registered at any given time. 
 * 
 */
public interface IStubControl {

    /** 
     * Registers stub reference for a specific class. 
     * 
     * @param <T>
     * @param stubRef
     * @param stubClassRef
     * @return 
     */
    <T> IStubControl addStub(T stubRef, Class<T> stubClassRef);

    /**
     * Removes a stub reference.
     * 
     * @param <T>
     * @param stubRef
     * @return 
     */
    <T> IStubControl delStub(T stubRef);

    /**
     * Populate 1 or more objects recursively. Any occurrences of setSomeName(X)
     * where the type of X has previously been configured via {@link addStub}
     * will be populated.
     * 
     * @param object
     * @return 
     */
    IStubControl populate(Object ... object);

    /**
     * Populate all stub objects that have been registered.
     * 
     * @return 
     * @see #populate(java.lang.Object...) 
     */
    IStubControl populateAll();

    /**
     * Removes a stub reference based upon stub and class type. 
     * 
     * @param <T>
     * @param stub
     * @param stubRef
     * @return 
     */
    <T> IStubControl delStub(T stub, Class<T> stubRef);
    
}
