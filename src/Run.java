

import java.io.IOException;
import java.util.Scanner;

/**
 * Основной класс приложения. Создает все экземпляры и запускает программу.
 * @author Нуруллаев Даниил p3114
 * @version 1.0
 */

public class Run {
    public static final String PS1 = "$";
    public static final String PS2 = ">";

    public static void main(String[] args) throws IOException {
        try (Scanner userScanner = new Scanner(System.in)) {
            String envVariable="";
            if (args.length==0){
                throw new ArrayIndexOutOfBoundsException();
            }
            if (args!=null || args.length!=0){
                envVariable=args[0];
            }

            MarineChecker marineAsker = new MarineChecker(userScanner);
            FileParser fileParser = new FileParser(envVariable);
            CollectionManager collectionManager = new CollectionManager(fileParser);
            CommandManager commandManager = new CommandManager(
                    new ExitCommand(),
                    new HelpCommand(),
                    new InfoCommand(collectionManager),
                    new ShowCommand(collectionManager),
                    new AddCommand(collectionManager,marineAsker),
                    new UpdateCommand(collectionManager,marineAsker),
                    new RemoveByIdCommand(collectionManager),
                    new ClearCommand(collectionManager),
                    new ExecuteScriptCommand(),
                    new SaveCommand(collectionManager),
                    new RemoveGreaterCommand(collectionManager,marineAsker),
                    new RemoveAtIndexCommand(collectionManager),
                    new InsertAtIndexCommand(collectionManager,marineAsker),
                    new FilterContainsNameCommand(collectionManager),
                    new FilterLessThanWeaponTypeCommand(collectionManager),
                    new PrintFieldDescendingHeightCommand(collectionManager)
            );
            Console console = new Console(commandManager, userScanner, marineAsker);
            console.interactiveMode();
        } catch (ArrayIndexOutOfBoundsException e){
            Console.printerror("Вы не указали аргумент!!!");
        }
    }

}


