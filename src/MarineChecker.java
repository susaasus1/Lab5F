

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Спрашивает пользователя о данных
 */
public class MarineChecker {
    private final Integer MIN_MARINES = 1;
    private final Integer MAX_MARINES = 1000;
    private final Float MIN_HEALTH = 0F;

    private Scanner userScanner;
    private boolean fileMode;

    public MarineChecker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    /**
     * Устанавливает сканер на сканирование пользовательского ввода
     * @param userScanner Сканер для пользовательского ввода
     */
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     *
     * @return Возврощает сканнер для пользовательского ввода
     */
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * Устанавливает MarineChecker на Файловый режим
     */
    public void setFileMode() {
        fileMode = true;
    }
    /**
     * Устанавливает MarineChecker на Пользовательский режим
     */
    public void setUserMode() {
        fileMode = false;
    }

    /**
     * Спрашивает имя солдата
     * @return Имя солдата
     * @throws IncorrectInputInScriptException Если скрипт запущен и что-то пошло не так.
     */
    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Console.println("Введите имя:");
                Console.print(Run.PS2);
                name = userScanner.nextLine().trim();
                if (fileMode) Console.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("Имя не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Спрашивает координату X местонохождения солдата
     * @return Координату X солдата
     * @throws IncorrectInputInScriptException Если скрипт запущен и что-то пошло не так.
     */
    public float askX() throws IncorrectInputInScriptException {
        String strX;
        float x;
        while (true) {
            try {
                Console.println("Введите координату X:");
                Console.print(Run.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println(strX);
                x = Float.parseFloat(strX);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата X должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;
    }
    /**
     * Спрашивает координату Y местонохождения солдата
     * @return Координату Y солдата
     * @throws IncorrectInputInScriptException Если скрипт запущен и что-то пошло не так.
     */
    public int askY() throws IncorrectInputInScriptException {
        String strY;
        int y;
        while (true) {
            try {
                Console.println("Введите координату Y:");
                Console.print(Run.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode) Console.println(strY);
                y = Integer.parseInt(strY);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }
    /**
     * Спрашивает координаты солдата
     * @return Координаты солдата
     * @throws IncorrectInputInScriptException Если скрипт запущен и что-то пошло не так.
     */
    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        float x;
        int y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }
    /**
     * Спрашивает здоровье солдата
     * @return здоровье солдата
     * @throws IncorrectInputInScriptException Если скрипт запущен и что-то пошло не так.
     */
    public Float askHealth() throws IncorrectInputInScriptException {
        String strHealth;
        Float health;
        while (true) {
            try {
                Console.println("Введите здоровье:");
                Console.print(Run.PS2);
                strHealth = userScanner.nextLine().trim();
                if (fileMode) Console.println(strHealth);
                health = Float.parseFloat(strHealth);
                if (health < MIN_HEALTH) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Здоровье должно быть больше нуля!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Здоровье должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return health;
    }

    /**
     * Спрашивает рост солдата
     * @return Здоровье солдата
     * @throws IncorrectInputInScriptException Если скрипт запущен и что-то пошло не так.
     */
    public double askHeight() throws IncorrectInputInScriptException {
        String strHeight;
        double height;
        while (true) {
            try {
                Console.println("Введите рост:");
                Console.print(Run.PS2);
                strHeight = userScanner.nextLine().trim();
                if (fileMode) Console.println(strHeight);
                height = Double.parseDouble(strHeight);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Рост должен быть представлен числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return height;
    }

    /**
     * Спрашивает категорию солдата
     * @return Категорию солдата
     * @throws IncorrectInputInScriptException Если скрипт запущен и что-то пошло не так.
     */
    public AstartesCategory askCategory() throws IncorrectInputInScriptException {
        String strCategory;
        AstartesCategory category;
        while (true) {
            try {
                Console.println("Список категорий - " + AstartesCategory.nameList());
                Console.println("Введите категорию:");
                Console.print(Run.PS2);
                strCategory = userScanner.nextLine().trim();
                if (fileMode) Console.println(strCategory);
                category = AstartesCategory.valueOf(strCategory.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("Категории нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return category;
    }

    /**
     * Спрашивает тип оружия солдата
     * @return Тип оружия солдата
     * @throws IncorrectInputInScriptException Если скрипт запущен и что-то пошло не так.
     */
    public Weapon askWeaponType() throws IncorrectInputInScriptException {
        String strWeaponType;
        Weapon weaponType;
        while (true) {
            try {
                Console.println("Список оружия дальнего боя - " + Weapon.nameList());
                Console.println("Введите оружие дальнего боя:");
                Console.print(Run.PS2);
                strWeaponType = userScanner.nextLine().trim();
                if (fileMode) Console.println(strWeaponType);
                weaponType = Weapon.valueOf(strWeaponType.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("Оружия нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return weaponType;
    }

    /**
     * Спрашивает название служебной части солдата
     * @return Название служебной части солдата
     * @throws IncorrectInputInScriptException Если скрипт запущен и что-то пошло не так.
     */
    public String askChapterName() throws IncorrectInputInScriptException {
        String chapterName;
        while (true) {
            try {
                Console.println("Введите имя ордена:");
                Console.print(Run.PS2);
                chapterName = userScanner.nextLine().trim();
                if (fileMode) Console.println(chapterName);
                if (chapterName.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("Имя ордена не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return chapterName;

    }
    /**
     * Спрашивает название изначальной служебной части солдата
     * @return Название изначальной служебной части солдата
     * @throws IncorrectInputInScriptException Если скрипт запущен и что-то пошло не так.
     */
    public String askChapterParentLegion() throws IncorrectInputInScriptException {
        String chapterParentLegion;
        while (true) {
            try {
                Console.println("Введите ParentLegion:");
                Console.print(Run.PS2);
                chapterParentLegion = userScanner.nextLine().trim();
                if (fileMode) Console.println(chapterParentLegion);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return chapterParentLegion;
    }
    /**
     * Спрашивает количество солдат в служебной части солдата
     * @return Количество солдат в служебной части солдата
     * @throws IncorrectInputInScriptException Если скрипт запущен и что-то пошло не так.
     */
    public Integer askChapterMarinesCount() throws IncorrectInputInScriptException {
        String strMarinesCount;
        Integer marinesCount;
        while (true) {
            try {
                Console.println("Введите количество солдат в ордене < " + (MAX_MARINES+1) + ":");
                Console.print(Run.PS2);
                strMarinesCount = userScanner.nextLine().trim();
                if (fileMode) Console.println(strMarinesCount);
                marinesCount = Integer.parseInt(strMarinesCount);
                if (marinesCount < MIN_MARINES || marinesCount > MAX_MARINES) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Количество солдат в ордене должно быть положительным и не превышать " + MAX_MARINES + "!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("Количество солдат в ордене должно быть представлено числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return marinesCount;
    }
    /**
     * Спрашивает мир служебной части солдата
     * @return Мир служебной части солдата
     * @throws IncorrectInputInScriptException Если скрипт запущен и что-то пошло не так.
     */
    public String askChapterWorld() throws IncorrectInputInScriptException {
        String chapterWorld;
        while (true) {
            try {
                Console.println("Введите название мира:");
                Console.print(Run.PS2);
                chapterWorld = userScanner.nextLine().trim();
                if (fileMode) Console.println(chapterWorld);
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return chapterWorld;
    }
    /**
     * Спрашивает служебную часть солдата
     * @return Служебную часть солдата
     * @throws IncorrectInputInScriptException Если скрипт запущен и что-то пошло не так.
     */
    public Chapter askChapter() throws IncorrectInputInScriptException {
        String name,world,parentLegion;
        Integer marinesCount;

        name = askChapterName();
        parentLegion=askChapterParentLegion();
        world=askChapterWorld();
        marinesCount = askChapterMarinesCount();
        return new Chapter(name,parentLegion, marinesCount,world);
    }
    @Override
    public String toString() {
        return "MarineAsker (вспомогательный класс для запросов пользователю)";
    }

    /**
     * Задает вопрос пользователю
     * @param question Вопрос
     * @return Вопрос(true/false)
     * @throws IncorrectInputInScriptException Если скрипт запущен и что-то пошло не так.
     */
    public boolean askQuestion(String question) throws IncorrectInputInScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                Console.print(Run.PS2);
                answer = userScanner.nextLine().trim();
                if (fileMode) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("Ответ должен быть представлен знаками '+' или '-'!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return (answer.equals("+")) ? true : false;
    }
}
