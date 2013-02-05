import org.apache.chemistry.opencmis.commons.*
import org.apache.chemistry.opencmis.commons.data.*
import org.apache.chemistry.opencmis.commons.enums.*
import org.apache.chemistry.opencmis.client.api.*

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
def base = getBaseFolder(root, "/Path"); // customize here
def children = getNodes(base);
deleteNodes(children);