

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * Управляет вводом команд
 */
public class Console {

    private CommandManager commandManager;
    private Scanner userScanner;
    private MarineChecker marineAsker;
    private List<String> scriptStack=new ArrayList<>();
    boolean t=false;

    public Console(CommandManager commandManager, Scanner userScanner, MarineChecker marineAsker) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
        this.marineAsker = marineAsker;
    }

    /**
     * Мод для захвата комманд из скрипта
     * @param argument Этот аргумент
     * @return Финальный код
     */
    public int scriptMode(String argument) {
        String[] userCommand = {"", ""};
            int commandStatus = 0;
        scriptStack.add(argument);
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = marineAsker.getUserScanner();
            marineAsker.setUserScanner(scriptScanner);
            marineAsker.setFileMode();
            do {
                
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                Console.println(Run.PS1 + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) {
                            t=true;
                        };
                    }
                }
                
                if (t==true){
                    t=false;
                    Console.printerror("Скрипты не могут вызываться рекурсивно!");
                }
                else {
                    commandStatus = launchCommand(userCommand);
                    System.out.println(commandStatus);
                }
            } while (commandStatus == 0 && scriptScanner.hasNextLine());
            marineAsker.setUserScanner(tmpScanner);
            marineAsker.setUserMode();
            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                Console.println("Проверьте скрипт на корректность введенных данных!");
            return commandStatus;
        } catch (FileNotFoundException exception) {
            Console.printerror("Файл со скриптом не найден!");
        } catch (NoSuchElementException exception) {
            Console.printerror("Файл со скриптом пуст!");
        }
             catch (IllegalStateException exception) {
            Console.printerror("Непредвиденная ошибка!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size()-1);
        }
        return 1;
    }

    /**
     * Запускает команду
     * @param userCommand Команда для запуска
     * @return Финальный код
     */
    private int launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commandManager.help(userCommand[1])) return 1;
                break;
            case "info":
                if (!commandManager.info(userCommand[1])) return 1;
                break;
            case "show":
                if (!commandManager.show(userCommand[1])) return 1;
                break;
            case "add":
                if (!commandManager.add(userCommand[1])) return 1;
                break;
            case "update":
                if (!commandManager.update(userCommand[1])) return 1;
                break;
            case "remove_by_id":
                if (!commandManager.removeById(userCommand[1])) return 1;
                break;
            case "clear":
                if (!commandManager.clear(userCommand[1])) return 1;
                break;
            case "save":
                if (!commandManager.save(userCommand[1])) return 1;
                break;
            case "execute_script":
                if (!commandManager.executeScript(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            case "insert_at":
                if (!commandManager.insert(userCommand[1])) return 1;
                break;
            case "remove_greater":
                if (!commandManager.removeGreater(userCommand[1])) return 1;
                break;
            case "remove_at":
                if (!commandManager.removeAt(userCommand[1])) return 1;
                break;
            case "filter_contains_name":
                if (!commandManager.filterContainsName(userCommand[1])) return 1;
                break;
            case "filter_less_than_weapon_type":
                if (!commandManager.filterLessThanWeaponType(userCommand[1])) return 1;
                break;
            case "print_field_descending_height":
                if (!commandManager.printFieldDescendingHeight(userCommand[1])) return 1;
                break;
            case "exit":
                if (!commandManager.exit(userCommand[1])) return 1;
                else return 2;
            default:
                if (!commandManager.noSuchCommand(userCommand[0])) return 1;
        }
        return 0;
    }

    /**
     * Режим для захвата команд из пользовательского ввода
     */
    public void interactiveMode() {
        String[] userCommand = {"", ""};
        int commandStatus;
        try {
            do {
                Console.print(Run.PS1);
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != 2);
        } catch (NoSuchElementException exception) {
            Console.printerror("Пользовательский ввод не обнаружен!");
        } catch (IllegalStateException exception) {
            Console.printerror("Непредвиденная ошибка!");
        }
    }

    /**
     * Печатает toOut.toString() в консоль
     * @param toOut Объект для печати
     */
    public static void print(Object toOut) {
        System.out.print(toOut);
    }

    /**
     * Печатает error : toOut.toString() в консоль
     * @param toOut Ошибка для печати
     */
    public static void printerror(Object toOut) {
        System.out.println("error: " + toOut);
    }
    /**
     * Печатает toOut.toString()+\n в консоль
     * @param toOut Объект для печати
     */
    public static void println(Object toOut) {
        System.out.println(toOut);
    }

    /**
     * Печатает два отформотированных элемента в консоль
     * @param element1 Первый элемент
     * @param element2 Второй элемент
     */
    public static void printtable(Object element1, Object element2) {
        System.out.printf("%-45s%-1s%n", element1, element2);
    }

    @Override
    public String toString() {
        return "Console (класс для обработки ввода команд)";
    }
}
