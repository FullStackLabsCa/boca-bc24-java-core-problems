package problems.old_assignments.demo;

public class StringExample {
    public static void main(String[] args) {
        int a = 1;
        String anotherName = "Hello";

        String helloName = "Hello";
        String name = new String("Hello");

        System.out.println((anotherName == name));

        System.out.println(System.identityHashCode(anotherName));
        System.out.println(System.identityHashCode(name));
        System.out.println(System.identityHashCode(helloName));


        System.out.println("anotherName.equals(name) = " + anotherName.equals(name));

        StringBuffer stringBuilder = new StringBuffer();
        System.out.println("stringBuilder.append(\"Hello\").append(\" , World\").toString() = " + stringBuilder.append("Hello").append(" , World").toString());

        char x = 'a';
        char b = 'b';

        byte c = '1';
        byte d = '2';
        int e = (char) (c + d);

        Integer y = 12;

        //

        System.out.println(a + b);
        System.out.println(e);
        Hello hello = new Hello() {
            @Override
            public Ab getAb() {
                return super.getAb();
            }

            @Override
            public void setAb(Ab ab) {
                super.setAb(ab);
            }

            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public boolean equals(Object obj) {
                return super.equals(obj);
            }

            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }

            @Override
            public String toString() {
                return super.toString();
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        };

    }


}
