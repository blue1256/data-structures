import java.util.*;

public class myAssignment {
	public static void main(String[] args) {
		myLinkedList list = new myLinkedList();
		String menu = "Choice\tAction\n------\t------\nA\tAdd String\nC\tCheck if Empty\nE\tSearch for String\nL\tList Strings\nO\tList Current Size\nQ\tQuit\nR\tRemove String\nT\tReverse\nU\tRemove Duplicates\n?\tDisplay Help";
		Scanner console = new Scanner(System.in);
		System.out.println(menu);
		while(true) {
			int size = list.size();
			System.out.println("What action would you like to perform?");
			String ans = console.nextLine();
			ans = ans.toUpperCase();
			if(ans.equals("A")) {
				String data;
				int index=-1;
				System.out.println("Please enter a string to add:");
				data = console.nextLine();
				do {
					System.out.println("Please enter an index to add:");
					index = console.nextInt();
					console.nextLine();
					if(index<=size&&index>=0) {
						list.add(index, data);
						break;
					}
					System.out.println("The last index is "+size);
				} while(index>size||index<0);
			}
			if(ans.equals("C")) {
				if(list.isEmpty()) {
					System.out.println("The list is empty.");
				} else {
					System.out.println("The list contains some element(s).");
				}
			}
			if(ans.equals("E")) {
				int index;
				do {
					System.out.println("Please enter an index to search:");
					index = console.nextInt();
					console.nextLine();
					if(index<size) {
						System.out.println("string at the given index is "+list.get(index));
						break;
					}
					System.out.println("​the index you want is not in the list");
				} while(index>=size||index<0);
			}
			if(ans.equals("L")) {
				System.out.println(list.toString());
			}
			if(ans.equals("O")) {
				System.out.println("The current size is "+size);
			}
			if(ans.equals("Q")) {
				break;
			}
			if(ans.equals("R")) {
				int index;
				do {
					System.out.println("Please enter the index of a string to remove:");
					index = console.nextInt();
					console.nextLine();
					if(index<size) {
						System.out.println(list.remove(index)+" was removed");
						break;
					}
					System.out.println("It does not exist");
				} while(index>=size||index<0);
			}
			if(ans.equals("T")) {
				list.reverse();
				System.out.println("list reversed");
			}
			if(ans.equals("U")) {
				list.removeDuplicate();
			}
			if(ans.equals("?")) {
				System.out.println(menu);
			}
		}
		console.close();
	}
}
