package geekspearls.tree;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by aliu on 31/07/16.
 */
public class BSTTest {

    @Test
    public void testCreateBSTFromArray() {
        List<Integer> values = Arrays.asList(2, 33, 65 ,10, 8, 21, 4, 6);
        BST bst = new BST();
        bst.create(values);
        bst.dfsInOrder();
    }

    @Test
    public void testCreateBST() {
        int values[] = {2, 33, 65 ,10, 8, 21, 4, 6};
        BST bst = new BST();
        for (int value : values) {
            bst.insert(value);
        }
        bst.dfsInOrder();
    }

    @Test
    public void testCreateBSTRecur() {
        int values[] = {2, 33, 65 ,10, 8, 21, 4, 6};
        BST bst = new BST();
        bst.root = new Node(2);
        for (int i = 1; i < values.length; i++) {
            bst.insert(bst.root, values[i]);
        }
        bst.dfsInOrder();
    }

    @Test
    public void testFind() {
        int values[] = {2, 33, 65 ,10, 8, 21, 4, 6};
        BST bst = new BST();
        for (int value : values) {
            bst.insert(value);
        }
        Node found = bst.find(10);
        Assert.assertNotNull(found);
        Assert.assertEquals(found.value, 10);
    }

    @Test
    public void testDelete() {
        int values[] = {20, 10,1,11,2,8,5, 9};
        BST bst = new BST();
        for (int value : values) {
            bst.insert(value);
        }
        bst.delete(10);
        bst.delete(9);
        bst.delete(20);
        bst.delete(1);
        bst.delete(11);
        bst.delete(2);
//        bst.delete(8);
//        bst.delete(5);
        bst.dfsInOrder();
    }

    @Test
    public void testBfs() {
        int values[] = {20, 10,1,11,2,8,5, 9};
        BST bst = new BST();
        for (int value : values) {
            bst.insert(value);
        }
        bst.bfs();
    }

    @Test
    public void testDistance() {
        int values[] = {10, 12,1,11,20,8,5, 9};
        BST bst = new BST();
        for (int value : values) {
            bst.insert(value);
        }
        Assert.assertEquals(bst.distance(bst.root, 1, 8), 1);
        Assert.assertEquals(bst.distance(bst.root, 5, 11), 5);
        Assert.assertEquals(bst.distance(bst.root, 5, 21), -1);
        Assert.assertEquals(bst.distance(bst.root, 2, 11), -1);
        Assert.assertEquals(bst.distance(bst.root, 22, 2), -1);
    }

    @Test
    public void testLca() {
        int values[] = {10, 12,1,11,20,8,5, 9};
        BST bst = new BST();
        for (int value : values) {
            bst.insert(value);
        }
        Assert.assertEquals(bst.lca(bst.root, 1, 8), new Node(1));
        Assert.assertEquals(bst.lca(bst.root, 5, 11), new Node(10));
        Assert.assertNull(bst.lca(bst.root, 5, 21));
        Assert.assertNull(bst.lca(bst.root, 2, 11));
        Assert.assertNull(bst.lca(bst.root, 22, 2));
    }

}
