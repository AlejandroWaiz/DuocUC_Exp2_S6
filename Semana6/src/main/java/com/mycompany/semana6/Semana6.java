/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.semana6;

/**
 *
 * @author Coto
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;

class Entrada {
    private int numero;
    private String ubicacion;
    private double precio;
    private String tipo;
    private double descuento;
    private double precioFinal;
    private boolean reservada;

    public Entrada(int numero, String ubicacion, double precio, String tipo, double descuento) {
        this.numero = numero;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.tipo = tipo;
        this.descuento = descuento;
        this.precioFinal = calcularPrecioFinal();
        this.reservada = false;
    }

    public int getNumero() {
        return numero;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public String getTipo() {
        return tipo;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public boolean isReservada() {
        return reservada;
    }

    public void setReservada(boolean reservada) {
        this.reservada = reservada;
    }

    private double calcularPrecioFinal() {
        return precio - (precio * descuento / 100);
    }

    public void mostrarDetalles() {
        System.out.println("\nNúmero: " + numero);
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Precio: $" + precio);
        System.out.println("Tipo: " + tipo);
        System.out.println("Descuento: " + descuento + "%");
        System.out.println("Precio Final: $" + precioFinal);
        System.out.println("Estado: " + (reservada ? "Reservada" : "Comprada"));
    }
}

public class Semana6 {
    private static ArrayList<Entrada> entradas = new ArrayList<>();
    private static ArrayList<Entrada> carritoReserva = new ArrayList<>();
    private static ArrayList<Entrada> carritoPagadas = new ArrayList<>();

    public static void main(String[] args) {
        inicializarEntradas();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Reserva de entradas.");
            System.out.println("2. Venta de entradas.");
            System.out.println("3. Búsqueda de entradas.");
            System.out.println("4. Modificación de entradas.");
            System.out.println("5. Eliminación de entradas.");
            System.out.println("6. Pagar.");
            System.out.println("7. Salir.");
            System.out.print("\nSelecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    submenuReserva(scanner);
                    break;
                case 2:
                    ventaEntradas(scanner);
                    break;
                case 3:
                    busquedaEntradas(scanner);
                    break;
                case 4:
                    modificacionEntradas(scanner);
                    break;
                case 5:
                    eliminacionEntradas(scanner);
                    break;
                case 6:
                    pagar();
                    return; // Termina el programa después de pagar
                case 7:
                    System.out.println("\nSaliendo del programa...");
                    return; // Termina el programa
                default:
                    System.out.println("\nOpción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void inicializarEntradas() {
        for (int i = 1; i <= 60; i++) {
            String ubicacion;
            double precio;
            if (i >= 1 && i <= 10) {
                ubicacion = "VIP";
                precio = 100;
            } else if (i >= 11 && i <= 30) {
                ubicacion = "Platea";
                precio = 50;
            } else {
                ubicacion = "General";
                precio = 30;
            }
            entradas.add(new Entrada(i, ubicacion, precio, "Público General", 0));
        }
    }

    private static void submenuReserva(Scanner scanner) {
        while (true) {
            System.out.println("\nReserva de entradas:");
            System.out.println("1. Hacer una reserva.");
            System.out.println("2. Comprar una entrada reservada.");
            System.out.println("3. Eliminar reserva de una entrada.");
            System.out.println("4. Volver al menú principal.");
            System.out.print("\nSelecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    reservaEntrada(scanner);
                    break;
                case 2:
                    compraEntradaReservada(scanner);
                    break;
                case 3:
                    eliminarReserva(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("\nOpción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void reservaEntrada(Scanner scanner) {
        System.out.println("\nElige una ubicación:");
        System.out.println("1. VIP (1-10) - Precio: $100");
        System.out.println("2. Platea (11-30) - Precio: $50");
        System.out.println("3. General (31-60) - Precio: $30");
        System.out.println("4. Volver al menú principal");
        System.out.print("\nSelecciona una opción: ");
        int ubicacionOpcion = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        if (ubicacionOpcion == 4) {
            return;
        }

        String ubicacion = "";
        int inicio = 0, fin = 0;
        switch (ubicacionOpcion) {
            case 1:
                ubicacion = "VIP";
                inicio = 1;
                fin = 10;
                break;
            case 2:
                ubicacion = "Platea";
                inicio = 11;
                fin = 30;
                break;
            case 3:
                ubicacion = "General";
                inicio = 31;
                fin = 60;
                break;
            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
                return;
        }

        System.out.println("\nSelecciona un número de asiento entre " + inicio + " y " + fin + ":");
        for (int i = inicio; i <= fin; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        int numeroAsiento = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        Entrada entradaSeleccionada = null;
        for (Entrada entrada : entradas) {
            if (entrada.getNumero() == numeroAsiento && entrada.getUbicacion().equals(ubicacion)) {
                entradaSeleccionada = entrada;
                break;
            }
        }

        if (entradaSeleccionada == null || entradaSeleccionada.isReservada()) {
            System.out.println("\nNúmero de asiento no disponible.");
            return;
        }

        System.out.println("\nSelecciona el tipo de entrada:");
        System.out.println("1. Público General (sin descuento)");
        System.out.println("2. Estudiante (10% descuento)");
        System.out.println("3. Tercera Edad (15% descuento)");
        System.out.println("4. Volver al menú principal");
        int tipoOpcion = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        if (tipoOpcion == 4) {
            return;
        }

        String tipo = "";
        double descuento = 0;
        switch (tipoOpcion) {
            case 1:
                tipo = "Público General";
                break;
            case 2:
                tipo = "Estudiante";
                descuento = 10;
                break;
            case 3:
                tipo = "Tercera Edad";
                descuento = 15;
                break;
            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
                return;
        }

        Entrada nuevaEntrada = new Entrada(numeroAsiento, ubicacion, entradaSeleccionada.getPrecio(), tipo, descuento);
        nuevaEntrada.setReservada(true);
        carritoReserva.add(nuevaEntrada);
        System.out.println("\nEntrada reservada.");

        // Marcar la entrada original como reservada
        for (Entrada entrada : entradas) {
            if (entrada.getNumero() == numeroAsiento) {
                entrada.setReservada(true);
                break;
            }
        }
    }

    private static void compraEntradaReservada(Scanner scanner) {
            // Tu código previo
            int[] myIntArray = new int[entradas.size()]; 
            int index = 0;

            for (Entrada entrada : entradas) {
               if (entrada.isReservada()) {
                 myIntArray[index] = entrada.getNumero(); 
                    index++;
              }
            }

            // Imprimir en una sola línea
            String result = Arrays.stream(myIntArray)
                                  .limit(index) // Limitar al número de elementos válidos
                                 .mapToObj(String::valueOf)
                                 .collect(Collectors.joining(","));
            System.out.print("\nEntradas reservadas:");
            System.out.println(result);
            
        
        System.out.print("\nIntroduce el número de asiento reservado que deseas comprar: ");
        int numeroAsiento = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        Entrada entradaReservada = null;
        for (Entrada entrada : carritoReserva) {
            if (entrada.getNumero() == numeroAsiento) {
                entradaReservada = entrada;
                break;
            }
        }

        if (entradaReservada == null) {
            System.out.println("\nNúmero de asiento no encontrado en reservas.");
            return;
        }

        // Marcar la entrada como vendida
        entradaReservada.setReservada(false);
        carritoReserva.remove(entradaReservada);
        carritoPagadas.add(entradaReservada);

        // Actualizar la entrada en la lista de entradas
        for (Entrada entrada : entradas) {
            if (entrada.getNumero() == numeroAsiento) {
                entrada.setReservada(true);
                break;
            }
        }

        System.out.println("\nEntrada comprada.");
    }

    private static void eliminarReserva(Scanner scanner) {
        System.out.print("\nIntroduce el número de asiento reservado que deseas eliminar: ");
        int numeroAsiento = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        Entrada entradaReservada = null;
        for (Entrada entrada : carritoReserva) {
            if (entrada.getNumero() == numeroAsiento) {
                entradaReservada = entrada;
                break;
            }
        }

        if (entradaReservada != null) {
            carritoReserva.remove(entradaReservada);
            // Marcar la entrada como disponible en la lista de entradas
            for (Entrada entrada : entradas) {
                if (entrada.getNumero() == numeroAsiento) {
                    entrada.setReservada(false);
                    break;
                }
            }
            System.out.println("\nReserva eliminada.");
        } else {
            System.out.println("\nNúmero de asiento reservado no encontrado.");
        }
    }

    
    
    private static void ventaEntradas(Scanner scanner) {
        System.out.println("\nElige una ubicación:");
        System.out.println("1. VIP (1-10) - Precio: $100");
        System.out.println("2. Platea (11-30) - Precio: $50");
        System.out.println("3. General (31-60) - Precio: $30");
        System.out.println("4. Volver al menú principal");
        System.out.print("\nSelecciona una opción: ");
        int ubicacionOpcion = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        if (ubicacionOpcion == 4) {
            return;
        }

        String ubicacion = "";
        int inicio = 0, fin = 0;
        switch (ubicacionOpcion) {
            case 1:
                ubicacion = "VIP";
                inicio = 1;
                fin = 10;
                break;
            case 2:
                ubicacion = "Platea";
                inicio = 11;
                fin = 30;
                break;
            case 3:
                ubicacion = "General";
                inicio = 31;
                fin = 60;
                break;
            default:
                System.out.println("\nOpción no válida. Inténtalo de nuevo.");
                return;
        }

        System.out.println("\nSelecciona un número de asiento entre " + inicio + " y " + fin + ":");
        for (int i = inicio; i <= fin; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        int numeroAsiento = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        Entrada entradaSeleccionada = null;
        for (Entrada entrada : entradas) {
            if (entrada.getNumero() == numeroAsiento && entrada.getUbicacion().equals(ubicacion)) {
                entradaSeleccionada = entrada;
                break;
            }
        }

        if (entradaSeleccionada == null || entradaSeleccionada.isReservada()) {
            System.out.println("\nNúmero de asiento no disponible.");
            return;
        }

        System.out.println("\nSelecciona el tipo de entrada:");
        System.out.println("1. Público General (sin descuento)");
        System.out.println("2. Estudiante (10% descuento)");
        System.out.println("3. Tercera Edad (15% descuento)");
        System.out.println("4. Volver al menú principal");
        int tipoOpcion = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        if (tipoOpcion == 4) {
            return;
        }

        String tipo = "";
        double descuento = 0;
        switch (tipoOpcion) {
            case 1:
                tipo = "Público General";
                break;
            case 2:
                tipo = "Estudiante";
                descuento = 10;
                break;
            case 3:
                tipo = "Tercera Edad";
                descuento = 15;
                break;
            default:
                System.out.println("\nOpción no válida. Inténtalo de nuevo.");
                return;
        }

        Entrada nuevaEntrada = new Entrada(numeroAsiento, ubicacion, entradaSeleccionada.getPrecio(), tipo, descuento);
        nuevaEntrada.setReservada(false);
        carritoPagadas.add(nuevaEntrada);
        System.out.println("\nEntrada comprada.");

        // Marcar la entrada original como reservada
        for (Entrada entrada : entradas) {
            if (entrada.getNumero() == numeroAsiento) {
                entrada.setReservada(true);
                break;
            }
        }
    }

    private static void busquedaEntradas(Scanner scanner) {
        System.out.print("\nIntroduce el número de asiento que deseas buscar: ");
        int numeroAsiento = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        for (Entrada entrada : entradas) {
            if (entrada.getNumero() == numeroAsiento) {
                entrada.mostrarDetalles();
                return;
            }
        }
        System.out.println("\nNúmero de asiento no encontrado.");
    }

    private static void modificacionEntradas(Scanner scanner) {
        System.out.print("\nIntroduce el número de asiento de la entrada que deseas modificar: ");
        int numeroAsiento = scanner.nextInt();
        scanner.nextLine();  

        Entrada entradaAModificar = null;
        for (Entrada entrada : entradas) {
            if (entrada.getNumero() == numeroAsiento) {
                entradaAModificar = entrada;
                break;
            }
        }

        if (entradaAModificar == null) {
            System.out.println("\nNúmero de asiento no encontrado.");
            return;
        }

        if (entradaAModificar.isReservada()) {
            System.out.println("\nLa entrada está reservada o vendida y no puede ser modificada.");
            return;
        }

        System.out.println("\nElige una nueva ubicación:");
        System.out.println("1. VIP (1-10) - Precio: $100");
        System.out.println("2. Platea (11-30) - Precio: $50");
        System.out.println("3. General (31-60) - Precio: $30");
        System.out.println("4. Volver al menú principal");
        System.out.print("\nSelecciona una opción: ");
        int ubicacionOpcion = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        if (ubicacionOpcion == 4) {
            return;
        }

        String nuevaUbicacion = "";
        double nuevoPrecio = 0;
        switch (ubicacionOpcion) {
            case 1:
                nuevaUbicacion = "VIP";
                nuevoPrecio = 100;
                break;
            case 2:
                nuevaUbicacion = "Platea";
                nuevoPrecio = 50;
                break;
            case 3:
                nuevaUbicacion = "General";
                nuevoPrecio = 30;
                break;
            default:
                System.out.println("\nOpción no válida. Inténtalo de nuevo.");
                return;
        }

        // Eliminar la entrada original de la lista de entradas
        for (Entrada entrada : entradas) {
            if (entrada.getNumero() == numeroAsiento) {
                entrada.setReservada(false); // Marcar como no reservada
                break;
            }
        }

        // Crear la nueva entrada
        Entrada nuevaEntrada = new Entrada(numeroAsiento, nuevaUbicacion, nuevoPrecio, entradaAModificar.getTipo(), entradaAModificar.getDescuento());
        entradas.remove(entradaAModificar);
        entradas.add(nuevaEntrada);

        System.out.println("\nEntrada modificada.");
    }

    private static void eliminacionEntradas(Scanner scanner) {
        System.out.print("\nIntroduce el número de asiento que deseas eliminar: ");
        int numeroAsiento = scanner.nextInt();
        scanner.nextLine();  // Consumir el salto de línea

        Entrada entradaAEliminar = null;
        for (Entrada entrada : entradas) {
            if (entrada.getNumero() == numeroAsiento) {
                entradaAEliminar = entrada;
                break;
            }
        }

        if (entradaAEliminar != null) {
            if (entradaAEliminar.isReservada()) {
                System.out.println("\nLa entrada está reservada y no puede ser eliminada.");
                return;
            }

            entradas.remove(entradaAEliminar);
            System.out.println("\nEntrada eliminada.");
        } else {
            System.out.println("\nNúmero de asiento no encontrado.");
        }
    }

    private static void pagar() {
        double total = 0;
        
        System.out.println("\nReservas sin pagar:");
        for (Entrada entrada : carritoReserva) {
            entrada.mostrarDetalles();
            System.out.println();
        }
        
        for (Entrada entrada : carritoPagadas) {
            total += entrada.getPrecioFinal();
        }
        System.out.println("\nBoleta de Compra:");
        for (Entrada entrada : carritoPagadas) {
            entrada.mostrarDetalles();
            System.out.println();
        }
        System.out.println("Total a pagar: $" + total);
        System.out.println("Que tengas un buen dia!");
        carritoReserva.clear();
    }
}











