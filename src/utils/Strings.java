package utils;

public class Strings {


    public static void splashScreen() {
        System.out.println("\u001B[36m" + "█████      ████    ██████  ██████  ██     █████   █████   ██  ██  ██████  █████   █████ ");
        System.out.println("\u001B[36m" + "██  ██    ██  ██     ██      ██    ██     ██      ██      ██  ██    ██    ██  ██  ██    ");
        System.out.println("\u001B[36m" + "█████     ██████     ██      ██    ██     █████    ███    ██████    ██    █████    ███  ");
        System.out.println("\u001B[36m" + "██   ██   ██  ██     ██      ██    ██     ██         ██   ██  ██    ██    ██         ██ ");
        System.out.println("\u001B[36m" + "██████    ██  ██     ██      ██    █████  █████   █████   ██  ██  ██████  ██      █████ ");
        System.out.println("\u001B[0m");
    }

    public static void youWin() {
        System.out.println("\u001B[32m" + "██    ██  ██████  ██  ██       ██       ███       ██  ██  ███     ██ ");
        System.out.println("\u001B[32m" + "██    ██  ██  ██  ██  ██        ██     ██ ██     ██   ██  ██ ██   ██ ");
        System.out.println("\u001B[32m" + "████████  ██  ██  ██  ██         ██   ██   ██   ██    ██  ██  ██  ██ ");
        System.out.println("\u001B[32m" + "   ██     ██  ██  ██  ██          ██ ██     ██ ██     ██  ██   ██ ██ ");
        System.out.println("\u001B[32m" + "   ██     ██████  ██████           ███       ███      ██  ██     ███ ");
        System.out.println("\u001B[0m");

    }

    public static void youLose() {
        System.out.println("\u001B[31m" + "████    █████  █████  █████   ████   ██████  █████  ████   ");
        System.out.println("\u001B[31m" + "██  ██  ██     ██     ██     ██  ██    ██    ██     ██  ██ ");
        System.out.println("\u001B[31m" + "██  ██  █████  █████  █████  ██████    ██    █████  ██  ██ ");
        System.out.println("\u001B[31m" + "██  ██  ██     ██     ██     ██  ██    ██    ██     ██  ██ ");
        System.out.println("\u001B[31m" + "████    █████  ██     █████  ██  ██    ██    █████  ████   ");
        System.out.println("\u001B[0m");
    }
}
