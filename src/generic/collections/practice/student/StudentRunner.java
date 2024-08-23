package generic.collections.practice.student;

@SuppressWarnings("java:S106")
public class StudentRunner extends StudentData<Integer, String> {
    public static void main(String[] args) {
        StudentData<Integer, String> student1 = new StudentData<>();
        StudentData<Integer, String> student2 = new StudentData<>();
        StudentData<Integer, String> student3 = new StudentData<>();

        student1.setName("Harry");
        student1.setRollNo(1);
        student1.setAge(18);
        student1.setAddress("Brampton");

        student2.setName("Aman");
        student2.setRollNo(25);
        student2.setAge(35);
        student2.setAddress("Oakville");

        student3.setName("Naveen");
        student3.setRollNo(3);
        student3.setAge(31);
        student3.setAddress("Etobicoke");


//        //ArrayList example
//        System.out.println("----------With Arrays List -----------");
//        List<StudentData<Integer, String>> studentWithArrayList = new ArrayList<>();
//        studentWithArrayList.add(student1);
//        studentWithArrayList.add(student2);
//        studentWithArrayList.add(student3);
//
//        for (StudentData<Integer, String> student : studentWithArrayList) {
//            System.out.println("student = " + student);
//        }
//
//        //HashMap
//        System.out.println("----------With Hash Map -----------");
//        Map<Integer, StudentData<Integer, String>> studentMap = new HashMap<>();
//        studentMap.put(1, student1);
//        studentMap.put(2, student2);
//        studentMap.put(3, student3);
//
//        studentMap.forEach((key, value) -> {
//            System.out.println("key = " + key);
//            System.out.print("value = " + value);
//        });
//
//        //HashSet
//        System.out.println("---------With Hash Set -----------");
//        Set<StudentData<Integer, String>> studentHashSet = new HashSet<>();
//        studentHashSet.add(student1);
//        studentHashSet.add(student2);
//        studentHashSet.add(student3);
////        studentHashSet.add(student3);
//
//        System.out.println("studentHashSet = " + studentHashSet);
//
//        //TreeSet
//        System.out.println("---------With TreeSet -----------");
//        Set<StudentData<Integer, String>> studentTreeSet = new TreeSet<>();
//        studentTreeSet.add(student1);
//        studentTreeSet.add(student2);
//        studentTreeSet.add(student3);
//
//        for (StudentData<Integer, String> student : studentTreeSet) {
//            System.out.println("student = " + student);
//        }

        //TreeMap
//        System.out.println("--------With TreeMap -----------");
//        TreeMap<Integer, StudentData<Integer, String>> studentTreeMap = new TreeMap<>();
//        studentTreeMap.put(3, student3);
//        studentTreeMap.put(1, student1);
//        studentTreeMap.put(2, student2);
//
//        System.out.println("studentTreeMap = " + studentTreeMap);
//
//        studentTreeMap.forEach((key, value) -> {
//            System.out.println("key = " + key);
//            System.out.println("value = " + value);
//        });

//        //Queue
//        System.out.println("--------With Queue -----------");
//        Queue<StudentData<Integer, String>> studentQueue = new LinkedList<>();
//        studentQueue.add(student1);
//        studentQueue.add(student2);
//        studentQueue.add(student3);
//
//        System.out.println("studentQueue = " + studentQueue);

    }
}
