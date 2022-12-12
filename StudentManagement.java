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
            tree.insert(st);
        }
        return true;
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
            tree.delete(st);
            return true;
        }
        return false;
    }

    // Requirement 4
    public void undo() {
        undoState.push(tree.getRoot());
    }

    // Requirement 5
    public void redo() {
        // code here
    }

    // Requirement 6
    public ScoreAVL scoreTree(AVL tree) {
        return null;
    }
}
