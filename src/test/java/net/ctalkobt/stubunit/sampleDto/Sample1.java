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
package net.ctalkobt.stubunit.sampleDto;

import net.ctalkobt.stubunit.IStubControl;
import net.ctalkobt.stubunit.StubUnit;
import net.ctalkobt.stubunit.sampleDto.sample1.ChildDTO;
import net.ctalkobt.stubunit.sampleDto.sample1.ParentDTO;
import org.junit.Test;
import org.junit.Assert;

public class Sample1 {

    /**
     * Validate that the child will be populated within the parentDto
     * just by registering it. 
     */
    @Test
    public void Sample1Test() {
        ChildDTO child = new ChildDTO();
        ParentDTO parent = new ParentDTO();

        IStubControl ctl = StubUnit.createControl();
        ctl.addStub(child, ChildDTO.class);
        ctl.populate(parent, child);

        Assert.assertNotNull("Parent's child should not be null", parent.getChildDTO());

        /**
         * Child should not have been updated as parent was never registered as
         * a stub.
         */
        Assert.assertNull("Child's parent reference should be null", child.getParentDTO());
    }

}
