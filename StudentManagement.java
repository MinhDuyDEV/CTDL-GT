import java.util.*;

public class StudentManagement {
    private AVL tree;
    private Stack<Node> undoState;
    private Stack<Node> redoState;

    public StudentManagement() {
        this.tree = new AVL();
        this.undoState = new Stack<Node>();
        this.redoState = new Stack<Node>();
    }

    public AVL getTree() {
        return this.tree;
    }

    // Requirement 1
    public boolean addStudent(Student st) {
        if (tree == null) {
            return false;
        } else {
            Node clone = cloneBinaryTree(tree.getRoot());
            undoState.push(clone);
            tree.insert(st);
            return true;
        }
    }

    // Requirement 2
    public Student searchStudentById(int id) {
        Node found = tree.search(id);
        return (found == null) ? null : found.getData();
    }

    // Requirement 3
    public boolean removeStudent(int id) {
        Student st = searchStudentById(id);
        if (st != null) {
            Node clone = cloneBinaryTree(tree.getRoot());
            undoState.push(clone);
            tree.delete(st);
            return true;
        }
        return false;
    }

    public Node cloneBinaryTree(Node root) {
        if (root == null) {
            return null;
        }
        Node root_copy = new Node(root.getData());
        root_copy.setLeft(cloneBinaryTree(root.getLeft()));
        root_copy.setRight(cloneBinaryTree(root.getRight()));
        root_copy.setHeight(root.getHeight());
        return root_copy;
    }

    // Requirement 4
    public void undo() {
        redoState.push(tree.getRoot());
        Node cloneRoot = undoState.pop();
        this.tree.setRoot(cloneRoot);
    }

    // Requirement 5
    public void redo() {
        Node cloneRoot = redoState.pop();
        this.tree.setRoot(cloneRoot);
    }

    // Requirement 6
    public ScoreAVL scoreTree(AVL tree) {
        ArrayList<Node> arrayList = levelOrder(tree);
        ScoreAVL treeScore = new ScoreAVL();
        for (Node child : arrayList) {
            treeScore.insert(child.getData());
        }
        return treeScore;
    }

    public ArrayList<Node> levelOrder(AVL tree) {
        if (tree == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<Node>();
        ArrayList<Node> arrayList = new ArrayList<Node>();
        queue.add(tree.getRoot());
        while (!queue.isEmpty()) {
            Node tempNode = queue.poll();
            arrayList.add(tempNode);
            if (tempNode.getLeft() != null) {
                queue.add(tempNode.getLeft());
            }
            if (tempNode.getRight() != null) {
                queue.add(tempNode.getRight());
            }
        }
        return arrayList;
    }
}
