class LinkNode
{
    public long ID,contact;
    public int age;
    public String fname,lname,birth,gender,add,bloodt; // items that we're going to retrive in linknode
    public LinkNode next; // next link in list

    public LinkNode(long a, String b,String c,String d,int e, String f,long g,String h,String i) // constructor
    {
            ID = a;
            fname = b;
            lname = c ;
            birth = d;
            age = e;
            gender = f;
            contact = g ;
            add = h ;
            bloodt = i ;

                                            // initialize data
                                            // (next is automatically set to null)
    }

    public void displayLink() // display ourself
    {
        System.out.println("\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
                                +"\n" +"Patient's ID: "+ID+"\n"+"Full Name: "+fname+" "+lname+"\n"+"date of birth: "+birth+"\n"+"age: "+age+"\n"+"Gender: "+gender+"\n"+"Contact number: "+contact+"\n"+
                "Address: "+add+"\n"+"Blood Type: "+bloodt+ "\n" +
                            "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

} // end class Link
