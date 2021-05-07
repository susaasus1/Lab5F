
import java.util.ArrayList;
import java.util.List;

/**
 * Управляет коммандами
 */
public class CommandManager {
    private List<Command> commands = new ArrayList<>();
    private Command helpCommand;
    private Command infoCommand;
    private Command showCommand;
    private Command addCommand;
    private Command updateCommand;
    private Command removeByIdCommand;
    private Command clearCommand;
    private Command saveCommand;
    private Command executeScriptCommand;
    private Command exitCommand;
    private Command insertCommand;
    private Command removeAtCommand;
    private Command removeGreaterCommand;
    private Command filterContainsNameCommand;
    private Command filterLessThanWeaponTypeCommand;
    private Command printFieldDescendingHeightCommand;

    public CommandManager(
            Command exitCommand,
            Command helpCommand,
            Command infoCommand,
            Command showCommand,
            Command addCommand,
            Command updateCommand,
            Command removeByIdCommand,
            Command clearCommand,
            Command executeScriptCommand,
            Command saveCommand,
            Command removeGreaterCommand,
            Command removeAtCommand,
            Command insertCommand,
            Command filterContainsNameCommand,
            Command filterLessThanWeaponTypeCommand,
            Command printFieldDescendingHeightCommand
    ) {
        this.exitCommand = exitCommand;
        this.helpCommand=helpCommand;
        this.infoCommand=infoCommand;
        this.showCommand=showCommand;
        this.addCommand=addCommand;
        this.updateCommand=updateCommand;
        this.removeByIdCommand=removeByIdCommand;
        this.clearCommand=clearCommand;
        this.executeScriptCommand=executeScriptCommand;
        this.saveCommand=saveCommand;
        this.removeGreaterCommand=removeGreaterCommand;
        this.removeAtCommand=removeAtCommand;
        this.insertCommand=insertCommand;
        this.filterContainsNameCommand=filterContainsNameCommand;
        this.filterLessThanWeaponTypeCommand=filterLessThanWeaponTypeCommand;
        this.printFieldDescendingHeightCommand=printFieldDescendingHeightCommand;
        commands.add(helpCommand);
        commands.add(infoCommand);
        commands.add(showCommand);
        commands.add(addCommand);
        commands.add(updateCommand);
        commands.add(removeByIdCommand);
        commands.add(clearCommand);
        commands.add(saveCommand);
        commands.add(executeScriptCommand);
        commands.add(exitCommand);
        commands.add(removeGreaterCommand);
        commands.add(removeAtCommand);
        commands.add(insertCommand);
        commands.add(filterContainsNameCommand);
        commands.add(filterLessThanWeaponTypeCommand);
        commands.add(printFieldDescendingHeightCommand);
    }

    /**
     *
     * @return список комманд мэнеджера
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     * Печатает когда команда не найден
     * @param command Команда не найдена
     * @return статус команд о закрытии команды
     */
    public boolean noSuchCommand(String command) {
        Console.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;
    }

    /**
     * Исполняет нужную команду
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
   public boolean info(String argument) {
       return infoCommand.execute(argument);
   }

    /**
     * Печатает информацию о всех команд
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
    public boolean help(String argument) {
        if (helpCommand.execute(argument)) {
            for (Command command : commands) {
                Console.printtable(command.getName(), command.getDescription());
            }
            return true;
        } else return false;
    }

    /**
     * Исполняет нужную команду
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
    public boolean removeGreater(String argument) {
        return removeGreaterCommand.execute(argument);
    }
    /**
     * Исполняет нужную команду
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
    public boolean show(String argument){
        return showCommand.execute(argument);
    }
    /**
     * Исполняет нужную команду
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
    public boolean add(String argument) {
        return addCommand.execute(argument);
    }
    /**
     * Исполняет нужную команду
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
    public boolean update(String argument) {
        return updateCommand.execute(argument);
    }
    /**
     * Исполняет нужную команду
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
    public boolean removeById(String argument) {
        return removeByIdCommand.execute(argument);
    }
    /**
     * Исполняет нужную команду
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }
    /**
     * Исполняет нужную команду
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
    public boolean save(String argument) {
        return saveCommand.execute(argument);
    }
    /**
     * Исполняет нужную команду
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
    public boolean exit(String argument) {
        return exitCommand.execute(argument);
    }
    /**
     * Исполняет нужную команду
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
    public boolean executeScript(String argument) {
        return executeScriptCommand.execute(argument);
    }
    /**
     * Исполняет нужную команду
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
    public boolean insert(String argument) {
        return insertCommand.execute(argument);
    }
    /**
     * Исполняет нужную команду
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
    public boolean removeAt(String argument) {
        return removeAtCommand.execute(argument);
    }
    /**
     * Исполняет нужную команду
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
    public boolean filterContainsName(String argument){
        return filterContainsNameCommand.execute(argument);
    }
    /**
     * Исполняет нужную команду
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
    public boolean filterLessThanWeaponType(String argument){
        return filterLessThanWeaponTypeCommand.execute(argument);
    }
    /**
     * Исполняет нужную команду
     * @param argument Этот аргумент
     * @return статус команд о закрытии команды
     */
    public boolean printFieldDescendingHeight(String argument){
        return printFieldDescendingHeightCommand.execute(argument);
    }

    @Override
    public String toString() {
        return "CommandManager (вспомогательный класс для работы с командами)";
    }
}
