
import java.util.*;

public class LinkList {

    private LinkNode first; // ref to first link on list

    public LinkList() // constructor
    {
        first = null; // no items on list yet
    }


    public void insert(long a, String b,String c,String d,int e, String f,long g,String h,String i)
    { // make new link
        LinkNode newLink = new LinkNode(a,b,c,d,e,f,g,h,i);
        newLink.next = first; // newLink --> old first
        first = newLink; // first --> newLink
    }
    public LinkNode getFirst() {
        return first;
    }

    public void displayList() {
        System.out.print("Patients Records:\n ");
        LinkNode current = first; // start at beginning of list

        while(current != null) // until end of list,
        {
            current.displayLink(); // print data
            current = current.next; // move to next link
        }
        System.out.println("");
    }

    public void searchID (LinkNode readNode, long searchItem) {

        while (readNode != null) {
            if (readNode.ID == searchItem) {
                System.out.println("Found ID number:"+searchItem +", Patient: " + readNode.fname +" "+readNode.lname+"\nthe patient's record is down below:" );
                readNode.displayLink();
                System.out.println();
            }
            readNode = readNode.next;
        }
    }

    public void deleteRecordID(long a){
        LinkNode p,q;
        p = first;
        q = first;
        while(q.ID != a && q != null){
            p = q;
            q = q.next;
        }
        p.next = q.next;
        q.next = null;
        System.out.println("Record "+a+" of patient: "+q.ID+"Successfully deleted!");
    }

    public boolean isEmpty() // true if list is empty
    {
        return (first==null);
    }

}