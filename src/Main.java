import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Estudiante {
    String Nombre, apellido, cedula;
    Double promedio;
    Integer edad;

    public String getCedula() {
        return cedula;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getNombre() {
        return Nombre;
    }

    public Estudiante(String nombre, String apellido, String cedula, Double promedio, Integer edad) {
        Nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.promedio = promedio;
        this.edad = edad;
    }
}

class examen {
    void Menu() {
        int opcion = 0;
        do {
            System.out.println("Ingrese el número corespondiente para aceder a la parte del examen");
            System.out.println("1) Parte I)");
            System.out.println("2) Parte II)");
            System.out.println("3) Parte III)");
            System.out.println("4) Parte IV)");
            System.out.println("5) Salir");
            opcion = leerdatosenteros();
            switch (opcion) {
                case 1:
                    ParteI();
                    break;
                case 2:
                    ParteII();
                    break;
                case 3:
                    ParteIII();
                    break;
                case 4:
                    ParteIV();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Dato ingresado invalido");
            }
        } while (opcion != 5);
    }

    void ParteI() {
        String Nombre, apellido, cédula;
        Double promedio;
        Integer edad;
        Double primediodetodoslosestudiantes = 0.0;
        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();

        System.out.println("1.\n" +
                "Realizar una función que permita cargar un listado de alumnos a partir de\n" +
                "un archivo de texto plano llamado “Estudiantes.in”, con los datos\n" +
                "pertenecientes a una clase “Estudiante”, con atributos de Nombre, apellido,\n" +
                "cédula, promedio de notas, edad. Donde una vez cargados todos los\n" +
                "alumnos en la lista se imprima el promedio de notas general del listado.");
        try {
            BufferedReader br = new BufferedReader(new FileReader("Estudiantes.in"));
            try {
                while (br.ready()) {
                    for (int i = 0; i < 4; i++) {
                        Nombre = br.readLine();
                        apellido = br.readLine();
                        cédula = br.readLine();
                        promedio = Double.parseDouble(br.readLine());
                        try {
                            edad = Integer.parseInt(br.readLine());
                        } catch (NumberFormatException e) {
                            edad = 0;
                        }
                        estudiantes.add(new Estudiante(Nombre,
                                apellido, cédula, promedio, edad));
                        primediodetodoslosestudiantes += promedio;
                    }
                }
                primediodetodoslosestudiantes = primediodetodoslosestudiantes / estudiantes.size();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("El promedio de todos los estudiantes es: " + primediodetodoslosestudiantes);
    }

    void ParteII() {
        String Nombre, apellido, cedula;
        Double promedio;
        Integer edad;
        Double primediodetodoslosestudiantes = 0.0;
        DoublyLinkedList Lista = new DoublyLinkedList();
        System.out.println("2.\n" +
                "Realizar una función que permita cargar una lista circular doblemente\n" +
                "enlazada donde se pueda recorrer la misma aleatoriamente en cualquier\n" +
                "direccion y se muestre un elemento aleatorio de la misma.");
        try {
            BufferedReader br = new BufferedReader(new FileReader("Estudiantes.in"));
            try {
                while (br.ready()) {
                    for (int i = 0; i < 4; i++) {
                        Nombre = br.readLine();
                        apellido = br.readLine();
                        cedula = br.readLine();
                        promedio = Double.parseDouble(br.readLine());
                        try {
                            edad = Integer.parseInt(br.readLine());
                        } catch (NumberFormatException e) {
                            edad = 0;
                        }
                        Lista.append(new Estudiante(Nombre,
                                apellido, cedula, promedio, edad));
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Mostrando un elemento de forma aleatoria de la lista: " +
                Lista.getNodeAtPosition(((int) (Math.random() * Lista.getCount()))).getValue().getNombre());
    }

    public class DoublyLinkedList {
        private int count;
        private Node head;
        Node tail;

        public DoublyLinkedList() {
            this.setCount(0);
        }

        public int getCount() {
            return count;
        }

        public Estudiante getHead() {
            if (this.head == null) return null;
            return head.getValue();
        }

        public Estudiante getTail() {
            if (this.tail == null) return null;
            return this.tail.getValue();
        }

        public Estudiante getElementAtPosition(int position) {
            return this.getNodeAtPosition(position).getValue();
        }

        @Override
        public String toString() {
            String result = "[ ";
            boolean first = true;
            Node temp = this.head;
            if (temp == null) {
                return "Empty List";
            }

            do {
                if (first) {
                    first = false;
                } else {
                    result += ", ";
                }

                result += "\"" + temp.getValue() + "\"";
                temp = temp.getNext();
            } while (temp != null);
            result += " ]";

            return result;
        }

        public void print() {
            System.out.println(this.toString());
        }

        public boolean contains(Estudiante element) {
            for (int i = 0; i < this.getCount(); i++) {
                if (this.getElementAtPosition(i).equals(element)) {
                    return true;
                }
            }
            return false;
        }

        public void insert(Estudiante element) {
            Node newNode = new Node(element);

            this.setCount(this.getCount() + 1);
            newNode.setNext(head);

            if (this.head != null) {
                this.head.setPrev(newNode);
            }
            this.setHead(newNode);
            if (this.getCount() == 1) {
                this.setTail(newNode);
            }
        }

        public void insertAtPosition(int position, Estudiante element) {
            if (position < 0) {
                throw new IllegalArgumentException("Las posiciones negativas no están soportadas.");
            } else if (position == 0) {
                this.insert(element);
            } else if (position > this.getCount() - 1) {
                this.append(element);
            } else {
                this.setCount(this.getCount() + 1);
                Node tempNode;
                Node newNode = new Node(element);
                int positionsFromTheEnd = this.getCount() - position - 1;

                if (position <= positionsFromTheEnd) {
                    tempNode = this.head;
                    for (int i = 0; i < position; i++) {
                        tempNode = tempNode.getNext();
                    }
                    newNode.setPrev(tempNode.getPrev());
                    newNode.getPrev().setNext(newNode);
                    newNode.setNext(tempNode);
                    tempNode.setPrev(newNode);
                } else {
                    tempNode = this.tail;
                    for (int i = 0; i < positionsFromTheEnd; i++) {
                        tempNode = tempNode.getPrev();
                    }
                    newNode.setNext(tempNode.getNext());
                    newNode.getNext().setPrev(newNode);
                    newNode.setPrev(tempNode);
                    tempNode.setNext(newNode);
                }
            }
        }

        public void append(Estudiante element) {
            Node newNode = new Node(element);

            this.setCount(this.getCount() + 1);
            newNode.setPrev(tail);

            if (this.tail != null) {
                this.tail.setNext(newNode);
            }
            this.setTail(newNode);
            if (this.getCount() == 1) {
                this.setHead(newNode);
            }
        }

        public void replaceAtPosition(int position, Estudiante newElement) {
            Node replaceNode = this.getNodeAtPosition(position);
            replaceNode.setValue(newElement);
        }

        public boolean remove(Estudiante element) {
            Node removeNode = this.getNodeByValue(element);
            if (removeNode == null) return false;
            this.removeNode(removeNode);
            return true;
        }

        public Estudiante removeAtPosition(int position) {
            Node removeNode = this.getNodeAtPosition(position);
            this.removeNode(removeNode);
            return removeNode.getValue();
        }

        public void concat(DoublyLinkedList list) {
            if (this.tail != null) {
                this.tail.setNext(list.head);
            } else {
                this.setHead(list.head);
            }

            if (list.getCount() > 0) {
                list.head.setPrev(this.tail);
                this.setTail(list.tail);
            }
            this.setCount(this.getCount() + list.getCount());
        }

        private void setHead(Node head) {
            this.head = head;
        }

        private void setTail(Node tail) {
            this.tail = tail;
        }

        private void setCount(int count) {
            this.count = count;
        }

        private void removeNode(Node removeNode) {
            if (removeNode == null) return;

            this.setCount(this.getCount() - 1);
            Node prevNode = removeNode.getPrev();
            Node nextNode = removeNode.getNext();

            if (this.head == removeNode) this.setHead(nextNode);
            if (this.tail == removeNode) this.setTail(prevNode);
            if (prevNode != null) removeNode.getPrev().setNext(nextNode);
            if (nextNode != null) nextNode.setPrev(prevNode);
        }

        private Node getNodeByValue(Estudiante value) {
            Node tempNode;
            for (int i = 0; i < this.getCount(); i++) {
                tempNode = this.getNodeAtPosition(i);
                if (tempNode.getValue().edad > value.edad) {
                    return tempNode;
                }
            }
            return null;
        }

        private Node getNodeAtPosition(int position) {
            if (position < 0 || position >= this.getCount()) {
                System.out.println("Posición no valida");
            } else if (position == 0) {
                return this.head;
            } else if (position == this.getCount() - 1) {
                return this.tail;
            } else {
                Node tempNode;
                int positionsFromTheEnd = this.getCount() - position - 1;
                if (position <= positionsFromTheEnd) {
                    tempNode = this.head;
                    for (int i = 0; i < position; i++) {
                        tempNode = tempNode.getNext();
                    }
                } else {
                    tempNode = this.tail;
                    for (int i = 0; i < positionsFromTheEnd; i++) {
                        tempNode = tempNode.getPrev();
                    }
                }
                return tempNode;
            }
            return null;
        }
    }

    public class Node {
        private Node prev;
        private Node next;
        private Estudiante value;

        public Node(Estudiante value) {
            this.setValue(value);
        }

        public Node getPrev() {
            return prev;
        }

        public Node getNext() {
            return next;
        }

        public Estudiante getValue() {
            return value;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setValue(Estudiante value) {
            this.value = value;
        }

    }

    void ParteIII() {
        System.out.println("3.\n" +
                "Crear una función que permita determinar el valor mas alto, el mas bajo y la\n" +
                "mediana en una lista doblemente enlazada donde los nodos nada mas\n" +
                "posean sus enlaces y un atributo de “valor” que será el parámetro de\n" +
                "busqueda.");
        DoublyLinkedList Lista = new DoublyLinkedList();
        String Nombre, apellido, cédula;
        Double promedio;
        Integer edad;
        try {
            BufferedReader br = new BufferedReader(new FileReader("Estudiantes.in"));
            try {
                while (br.ready()) {
                    for (int i = 0; i < 4; i++) {
                        Nombre = br.readLine();
                        apellido = br.readLine();
                        cédula = br.readLine();
                        promedio = Double.parseDouble(br.readLine());
                        try {
                            edad = Integer.parseInt(br.readLine());
                        } catch (NumberFormatException e) {
                            edad = 0;
                        }
                        Lista.append(new Estudiante(Nombre,
                                apellido, cédula, promedio, edad));
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int edadminima = 200, edadmaxima = 0, average = 0, temp = 0, elements = 0;
        elements = Lista.getCount();
        for (int i = 0; i < elements; i++) {
            temp = Lista.getElementAtPosition(i).getEdad();
            edadmaxima = Integer.max(edadmaxima, temp);
            edadminima = Integer.min(edadminima, temp);
            average += temp;
        }
        average /= elements;
        System.out.println("La edas maxima es de: " + edadmaxima + "\n" +
                "la edad minima es de: " + edadminima + " el promedio de las edades es de: " + average);
    }

    List<Estudiante> eliminarconlacedula(List<Estudiante> estudiantes, String Cedula) {
        Estudiante e = null;
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCedula().equals(Cedula)) {
                e = estudiante;
            }
        }
        estudiantes.remove(e);
        List<Estudiante> temp = new ArrayList<Estudiante>();
        ;
        temp.addAll(estudiantes);
        return temp;
    }

    void mostrar(List<Estudiante> estudiantes) {
        System.out.print("Lista: [");
        for (Estudiante estudiante : estudiantes) {
            System.out.print(estudiante.getCedula() + " ");
        }
        System.out.println(" ]");
    }

    void ParteIV() {
        System.out.println("4.\n" +
                "Crear una función que permita eliminar elementos de una lista de personas,\n" +
                "usando como variable de busqueda la cédula de la misma, esta clase\n" +
                "persona poseera atributos de Nombre, Apellido y cédula (Mismos datos\n" +
                "pueden cargarse desde un archivo como en el ejercicio 1, o\n" +
                "cargarlos por la consola, queda asu criterio). Donde una vez cargadas\n" +
                "las personas a esa lista se solicite la cédula a eliminar, ya ingresado este\n" +
                "valor, la función deberá recorrer la lista de inicio a fin y eliminar todos los\n" +
                "elementos con esa misma cédula.");
        String Nombre, apellido, cédula;
        Double promedio;
        Integer edad;
        List<Estudiante> estudiantes = new ArrayList<Estudiante>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Estudiantes.in"));
            try {
                while (br.ready()) {
                    for (int i = 0; i < 4; i++) {
                        Nombre = br.readLine();
                        apellido = br.readLine();
                        cédula = br.readLine();
                        promedio = Double.parseDouble(br.readLine());
                        try {
                            edad = Integer.parseInt(br.readLine());
                        } catch (NumberFormatException e) {
                            edad = 0;
                        }
                        estudiantes.add(new Estudiante(Nombre,
                                apellido, cédula, promedio, edad));
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Mostraremos la lista antes de borrar (solo la cédula para no acupar muchas pantalla) :D");
        mostrar(estudiantes);
        System.out.println("Ingrese la cédula a eliminar de la lista");
        estudiantes = eliminarconlacedula(estudiantes, leertexto());//);
        System.out.println("Mostraremos la lista despues de borrar (solo la cédula para no acupar muchas pantalla) :D");
        System.out.print("Lista: [");
        mostrar(estudiantes);
    }

    int leerdatosenteros() {
        Scanner leer = new Scanner(System.in);
        return leer.nextInt();
    }

    String leertexto() {
        Scanner leer = new Scanner(System.in);
        return leer.nextLine();
    }

    void portada() {
        System.out.println("UNIVERSIDAD DE MARGARITA");
        System.out.println("ALMA MATER DEL CARIBE");
        System.out.println("Estructura de Datos T-01");
        System.out.println("Docente: Silvestre Cárdenas");
        System.out.println("Realizado por:Teilor Aguilar");
        System.out.println("Ci:29.819.816");
    }
}

public class Main {

    public static void main(String[] args) {
        examen e = new examen();
        e.portada();
        e.Menu();
    }


}