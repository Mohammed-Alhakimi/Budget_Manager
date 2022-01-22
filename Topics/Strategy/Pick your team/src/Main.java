import java.util.Scanner;

class SelectionContext {

    private PersonSelectionAlgorithm algorithm;

    public void setAlgorithm(PersonSelectionAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public Person[] selectPersons(Person[] persons) {
        return this.algorithm.select(persons);
    }
}

interface PersonSelectionAlgorithm {

    Person[] select(Person[] persons);
}

class TakePersonsWithStepAlgorithm implements PersonSelectionAlgorithm {

    int step;

    public TakePersonsWithStepAlgorithm(int step) {
        this.step = step;
    }

    @Override
    public Person[] select(Person[] persons) {
        if (step == 1) {
            return persons;
        } else {
            int countChosen = 0;
            for (int i = 0; i < persons.length; i++) {
                if (i % step == 0) {
                    countChosen++;
                }
            }
            Person[] chosen = new Person[countChosen];
            int j = 0;
            for (int i = 0; i < persons.length; i++) {
                if (i % step == 0) {
                    chosen[j] = persons[i];
                    j++;
                }
            }
            return chosen;
        }
    }
}


class TakeLastPersonsAlgorithm implements PersonSelectionAlgorithm {
    int count;

    public TakeLastPersonsAlgorithm(int count) {
        this.count = count;
    }

    @Override
    public Person[] select(Person[] persons) {
        if (count == 1) {
            return new Person[]{persons[persons.length - 1]};
        } else {
            int index = persons.length - count;
            Person[] arrayChosen = new Person[count];
            int j = 0;
            for (int i = index; i < persons.length; i++) {
                arrayChosen[j] = persons[i];
                j++;
            }
            return arrayChosen;
        }
    }
}

class Person {

    String name;

    public Person(String name) {
        this.name = name;
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int count = Integer.parseInt(scanner.nextLine());
        final Person[] persons = new Person[count];

        for (int i = 0; i < count; i++) {
            persons[i] = new Person(scanner.nextLine());
        }

        final String[] configs = scanner.nextLine().split("\\s+");

        final PersonSelectionAlgorithm alg = create(configs[0], Integer.parseInt(configs[1]));
        SelectionContext ctx = new SelectionContext();
        ctx.setAlgorithm(alg);

        final Person[] selected = ctx.selectPersons(persons);
        for (Person p : selected) {
            System.out.println(p.name);
        }
    }

    public static PersonSelectionAlgorithm create(String algType, int param) {
        switch (algType) {
            case "STEP": {
                return new TakePersonsWithStepAlgorithm(param);
            }
            case "LAST": {
                return new TakeLastPersonsAlgorithm(param);
            }
            default: {
                throw new IllegalArgumentException("Unknown algorithm type " + algType);
            }
        }
    }
}
/*
class Test{
    public static void main(String[] args) {
        int[] array = new int[]{0,1,2,3,4,5,6,7,8,9,10};
        System.out.println(Arrays.toString(selectLast(array, 8)));
        System.out.println(Arrays.toString(selectStep(array, 1)));

    }
    public static int[] selectLast(int[] persons,int count) {
        if (count == 1) {
            return new int[]{persons[persons.length-1]};
        } else {
            int index = persons.length-count;
            int[] arrayChosen = new int[count];
            int j=0;
            for (int i = index; i < persons.length; i++) {
                arrayChosen[j] = persons[i];
                j++;
            }
            return arrayChosen;
        }
    }
    public static int[] selectStep(int[] persons,int step) {
        if (step == 1) {
            return persons;
        } else {
            int countChosen = 0;
            for (int i = 0; i < persons.length; i++) {
                if (i % step == 0) {
                    countChosen++;
                }
            }
            int[] chosen = new int[countChosen];
            int j = 0;
            for (int i = 0; i < persons.length; i++) {
                if (i % step == 0) {
                    chosen[j] = persons[i];
                    j++;
                }
            }
            return chosen;
        }
    }
}*/
