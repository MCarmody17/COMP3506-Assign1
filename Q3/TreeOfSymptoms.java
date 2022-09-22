import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Objects;

class Symptom extends SymptomBase {

	public Symptom(String symptom, int severity) {
		super(symptom, severity);
	}

	@Override
	public int compareTo(SymptomBase o) {
		/* Add your code here! */
		return 0;
	}
}

public class TreeOfSymptoms extends TreeOfSymptomsBase {
	ArrayList<SymptomBase> arr = new ArrayList<>();

	public TreeOfSymptoms(SymptomBase root) {
		super(root);
	}

	@Override
	public ArrayList<SymptomBase> inOrderTraversal() {
		/* Add your code here! */
		//System.out.println(inOrder(getRoot()));
		return inOrder(getRoot());
	}

	public ArrayList<SymptomBase>  inOrder(SymptomBase root) {
		if(root ==  null) {
			return arr;
		}

		inOrder(root.getLeft());

		arr.add(root);
		inOrder(root.getRight());

		System.out.println(arr);
		return arr;

	}
	public ArrayList<SymptomBase>  PostOrder(SymptomBase root) {
		ArrayList<SymptomBase> arr = new ArrayList<>();
		if(root !=  null) {
			PostOrder(root.getLeft());
			PostOrder(root.getRight());
			arr.add(root);
		}
		return arr;
	}

	@Override
	public ArrayList<SymptomBase> postOrderTraversal() {
		return PostOrder(getRoot());
	}

	@Override
	public void restructureTree(int severity) {
		GFG g = new GFG();
		int min = 100;
		SymptomBase rooted = getRoot();
		ArrayList<SymptomBase> ret = new ArrayList<>();

		//Get root
		for(int i = 0; i < arr.size() ; i++){
			if (arr.get(i).getSeverity() < min && arr.get(i).getSeverity() <= severity){
				min = arr.get(i).getSeverity();
				rooted = (arr.get(i));
				setRoot(arr.get(i));
			}
		}

		setRoot(rooted);

		//System.out.println(rooted);
		//System.out.println(rooted.getLeft());
		//System.out.println(rooted.getRight());


		g.convertToMinHeap(rooted);
	}



	class GFG{
		private void bstToArray(SymptomBase root, ArrayList<SymptomBase> arr)
		{
			// ArrayLIst stores elements in inorder fashion
			if (root == null)
				return;

			bstToArray(root.getLeft(), arr);
			arr.add(root);
			bstToArray(root.getRight(), arr);
		}

		private void arrToMinHeap(SymptomBase root, ArrayList<SymptomBase> arr)
		{
			if (root == null)
				return;

			arrToMinHeap(root.getLeft(), arr);
			arrToMinHeap(root.getRight(), arr);
		}

		private void convertToMinHeap(SymptomBase root)
		{
			// initialize static index to zero
			ArrayList<SymptomBase> arr = new ArrayList<SymptomBase>();
			bstToArray(root, arr);
			arrToMinHeap(root, arr);
		}
	}

	public static void main(String[] args) {
		/*
		 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * REMOVE THE MAIN METHOD BEFORE SUBMITTING TO THE AUTOGRADER
		 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * The following main method is provided for simple debugging only
		 */
		var cough = new Symptom("Cough", 3);
		var fever = new Symptom("Fever", 6);
		var redEyes = new Symptom("Red Eyes", 1);

		redEyes.setLeft(cough);
		redEyes.setRight(fever);

		var tree = new TreeOfSymptoms(redEyes);
		var inOrderTraversal = tree.inOrderTraversal();
		var correctTraversal = new Symptom[] { cough, redEyes, fever };
		int i = 0;

		for (var patient : inOrderTraversal) {
			//System.out.println(patient);
			assert Objects.equals(patient, correctTraversal[i++]);
		}

		assert tree.getRoot() == redEyes;

		tree.restructureTree(2);

		inOrderTraversal = tree.inOrderTraversal();
		correctTraversal = new Symptom[] { redEyes, cough, fever};
		i = 0;
		for (var patient : inOrderTraversal) {
			//System.out.println(patient);
			assert Objects.equals(patient, correctTraversal[i++]);
		}
		assert tree.getRoot() == cough;

	}


}