/*
 * Copyright (C) 2013,2014 Carlo Sciolla
 *
 * Licensed under the MIT License ("the License")
 * You may obtain a copy of the 
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://github.com/skuro/enjoyable-content/blob/master/LICENSE.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.chemistry.opencmis.commons.*;
import org.apache.chemistry.opencmis.commons.data.*;
import org.apache.chemistry.opencmis.commons.enums.*;
import org.apache.chemistry.opencmis.client.api.*;

/*
 * Deletes all the children of a given folder. It will log all the file names that it will delete.
 *
 * Instructions:
 *   - configure the path of the folder you want to empty
 *   - run the script
 *
 */

def path = "/Path"; // customize here

def getBaseFolder(node, path) {
    session.getObjectByPath(path);
} 

def getNodes(node) {
    def ctx = session.createOperationContext();
    ctx.setMaxItemsPerPage(Integer.MAX_VALUE); // to sort of disable paging
    return node.getChildren(ctx);
}

def deleteNodes(children) {
    def iter = children.iterator();
    for (int i = 0; iter.hasNext(); i++) {
        def step = iter.next();
        println "[" + i + "] deleting " + step.getName();
        step.delete();
    }
}

def root = session.getRootFolder();
def base = getBaseFolder(root, path);
def children = getNodes(base);
deleteNodes(children);