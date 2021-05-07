
import java.io.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс который загружает коллекцию из файла и сохраняет в файл
 */
public class FileParser {
    LinkedList<SpaceMarine> spaceMarineLinkedList = new LinkedList<>();
    private String line;
    private final String source;
    private File file;
    private BufferedReader bufReader;
    private int count = 0;

    public FileParser(String source) throws FileNotFoundException {
        this.source = source;
        file = new File(source);
        try{
            Reader fileReader = new FileReader(file);
            bufReader = new BufferedReader(fileReader);
        } catch (IOException e) {
            Console.printerror("Файл не найден");
            System.exit(0);
        }

    }

    /**
     *
     * @return Возвращает загруженную из файла коллекцию
     * @throws IOException исключение ввода/вывода
     */
    public LinkedList<SpaceMarine> parse() throws IOException {
        try {
            if (!file.canRead()) throw new SecurityException();
            String tmp = null, name = null, parentLegion = null, world = null;
            Long id;
            Float health = 0F;
            double height = 0;
            float x = 0;
            int y = 0, year = 0, month = 0, dayOfMonth = 0;
            int countForCoordinates = 0, countForCreationDate = 0, countName = 0, countForChapter = 0;
            Integer marinesCount = 0;
            Long[] listId = new Long[1000];
            int counter=-1;
            boolean tId=false,tName=false,tChapterName=false,tYear=false,tMonth=false,tDay=false,tHealth=false,tWeaponType=false,tWorld=false,tCoordinates=false;

            Pattern pattern = Pattern.compile(">.+<");
            line = bufReader.readLine();

            while (line != null) {
                line = bufReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.contains("<SpaceMarine>")) {
                    countForChapter = 0;
                    name = null;
                    world = null;
                    parentLegion = null;
                    marinesCount = 0;
                    countForCoordinates = 0;
                    x=0;
                    y=0;
                    spaceMarineLinkedList.add(new SpaceMarine());
                    countName = 0;
                    tId=false;
                    tName=false;
                    tChapterName=false;
                    tMonth=false;
                    tDay=false;
                    tYear=false;
                    tHealth=false;
                    tWeaponType=false;
                    tWorld=false;
                    tCoordinates=false;
                    while (!line.contains("</SpaceMarine>")) {

                        if (line.contains("<id>")) {
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                tmp = line.substring(matcher.start() + 1, matcher.end() - 1);
                            }
                            id = Long.parseLong(tmp);
                            if (id<=0){
                                throw new NumberFormatException();
                            }
                            counter+=1;
                            for (int i = 0; i < listId.length; i++) {
                                if (listId[i] == id) {
                                    throw new IdNotUnique();
                                }
                            }
                            listId[counter]=id;

                            spaceMarineLinkedList.getLast().setId(id);
                            tId=true;
                        }
                        if ((line.contains("<name>")) && (countName == 1)) {
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                tmp = line.substring(matcher.start() + 1, matcher.end() - 1);
                            }
                            name = tmp;

                            spaceMarineLinkedList.getLast().setChapter(new Chapter(name, parentLegion, marinesCount, world));
                            tChapterName=true;
                        }
                        if ((line.contains("<name>")) && (countName == 0)) {
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                tmp = line.substring(matcher.start() + 1, matcher.end() - 1);
                            }
                            spaceMarineLinkedList.getLast().setName(tmp);
                            countName = 1;
                            tName=true;
                        }
                        if (line.contains("<x>")) {
                            countForCoordinates += 1;
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                tmp = line.substring(matcher.start() + 1, matcher.end() - 1);
                            }
                            x = Float.parseFloat(tmp);
                            spaceMarineLinkedList.getLast().setCoordinates(new Coordinates(x,y));
                        }
                        if (line.contains("<y>")) {
                            countForCoordinates += 1;
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                tmp = line.substring(matcher.start() + 1, matcher.end() - 1);
                            }
                            y = Integer.parseInt(tmp);
                            spaceMarineLinkedList.getLast().setCoordinates(new Coordinates(x,y));

                        }
                        if (countForCoordinates == 2) {
                            spaceMarineLinkedList.getLast().setCoordinates(new Coordinates(x, y));
                            countForCoordinates = 0;
                            x=0;
                            y=0;

                        }
                        if (line.contains("<year>")) {
                            countForCreationDate += 1;
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                tmp = line.substring(matcher.start() + 1, matcher.end() - 1);
                            }
                            year = Integer.parseInt(tmp);
                            tYear=true;
                        }
                        if (line.contains("<month>")) {
                            countForCreationDate += 1;
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                tmp = line.substring(matcher.start() + 1, matcher.end() - 1);
                            }
                            month = Integer.parseInt(tmp);
                            tMonth=true;
                        }
                        if (line.contains("<dayOfMonth>")) {
                            countForCreationDate += 1;
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                tmp = line.substring(matcher.start() + 1, matcher.end() - 1);
                            }
                            dayOfMonth = Integer.parseInt(tmp);
                            tDay=true;
                        }
                        if (countForCreationDate == 3) {
                            spaceMarineLinkedList.getLast().setCreationDate(LocalDate.of(year, month, dayOfMonth));
                            countForCreationDate = 0;
                        }
                        if (line.contains("<health>")) {
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                tmp = line.substring(matcher.start() + 1, matcher.end() - 1);
                            }
                            health = Float.parseFloat(tmp);
                            spaceMarineLinkedList.getLast().setHealth(health);
                            tHealth=true;
                        }
                        if (line.contains("<height>")) {
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                tmp = line.substring(matcher.start() + 1, matcher.end() - 1);
                            }
                            height = Double.parseDouble(tmp);
                            spaceMarineLinkedList.getLast().setHeight(height);
                        }
                        if (line.contains("<coordinates>")){
                            tCoordinates=true;
                        }
                        if (line.contains("<category>")) {
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                tmp = line.substring(matcher.start() + 1, matcher.end() - 1);
                            }
                            if (tmp.equalsIgnoreCase("DREADNOUGHT")) {
                                spaceMarineLinkedList.getLast().setCategory(AstartesCategory.DREADNOUGHT);
                            }
                            if (tmp.equalsIgnoreCase("INCEPTOR")) {
                                spaceMarineLinkedList.getLast().setCategory(AstartesCategory.INCEPTOR);
                            }
                            if (tmp.equalsIgnoreCase("LIBRARIAN")) {
                                spaceMarineLinkedList.getLast().setCategory(AstartesCategory.LIBRARIAN);
                            }
                            if (tmp.equalsIgnoreCase("CHAPLAIN")) {
                                spaceMarineLinkedList.getLast().setCategory(AstartesCategory.CHAPLAIN);
                            }
                            if (tmp.equalsIgnoreCase("HELIX")) {
                                spaceMarineLinkedList.getLast().setCategory(AstartesCategory.HELIX);
                            }

                        }
                        if (line.contains("<weaponType>")) {
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                tmp = line.substring(matcher.start() + 1, matcher.end() - 1);
                            }
                            if (tmp.equalsIgnoreCase("GRENADE_LAUNCHER")) {
                                spaceMarineLinkedList.getLast().setWeaponType(Weapon.GRENADE_LAUNCHER);
                            }
                            if (tmp.equalsIgnoreCase("HEAVY_FLAMER")) {
                                spaceMarineLinkedList.getLast().setWeaponType(Weapon.HEAVY_FLAMER);
                            }
                            if (tmp.equalsIgnoreCase("COMBI_FLAMER")) {
                                spaceMarineLinkedList.getLast().setWeaponType(Weapon.COMBI_FLAMER);
                            }
                            if (tmp.equalsIgnoreCase("MULTI_MELTA")) {
                                spaceMarineLinkedList.getLast().setWeaponType(Weapon.MULTI_MELTA);
                            }
                            tWeaponType=true;
                        }

                        if (line.contains("<parentLegion>")) {
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                tmp = line.substring(matcher.start() + 1, matcher.end() - 1);
                            }
                            parentLegion = tmp;
                            spaceMarineLinkedList.getLast().setChapter(new Chapter(name, parentLegion, marinesCount, world));
                            countForChapter += 1;

                        }
                        if (line.contains("<marinesCount>")) {
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                tmp = line.substring(matcher.start() + 1, matcher.end() - 1);
                            }
                            marinesCount = Integer.parseInt(tmp);
                            if (marinesCount<=0 || marinesCount>1000){
                                Console.println("Поле marinescount должно быть больше нуля и меньше ,либо равно 1000");
                                System.exit(0);
                            }
                            spaceMarineLinkedList.getLast().setChapter(new Chapter(name, parentLegion, marinesCount, world));
                            countForChapter += 1;

                        }
                        if (line.contains("<world>")) {
                            Matcher matcher = pattern.matcher(line);
                            if (matcher.find()) {
                                tmp = line.substring(matcher.start() + 1, matcher.end() - 1);
                            }
                            world = tmp;
                            countForChapter += 1;
                            spaceMarineLinkedList.getLast().setChapter(new Chapter(name, parentLegion, marinesCount, world));
                            tWorld=true;
                        }
                        if (countForChapter == 4) {
                            spaceMarineLinkedList.getLast().setChapter(new Chapter(name, parentLegion, marinesCount, world));
                            countForChapter = 0;
                            name = null;
                            world = null;
                            parentLegion = null;
                            marinesCount = 0;
                        }
                        line = bufReader.readLine();
                    }
                    if (tId==false){
                        Console.println("Поле ID не может быть Null");
                        System.exit(0);
                    }
                    if (tName==false){
                        Console.println("Поле Name не может быть Null");
                        System.exit(0);
                    }
                    if (tChapterName==false){
                        Console.println("Поле Name не может быть Null");
                        System.exit(0);
                    }
                    if (tDay==false){
                        Console.println("Поле CreationDate не может быть Null");
                        System.exit(0);
                    }
                    if (tMonth==false){
                        Console.println("Поле CreationDate не может быть Null");
                        System.exit(0);
                    }
                    if (tYear==false){
                        Console.println("Поле CreationDate не может быть Null");
                        System.exit(0);
                    }
                    if (tHealth==false){
                        Console.println("Поле Health не может быть Null");
                        System.exit(0);
                    }
                    if (tWeaponType==false){
                        Console.println("Поле WeaponType не может быть Null");
                        System.exit(0);
                    }
                    if (tWorld==false){
                        Console.println("Поле Chapter:World не может быть Null");
                        System.exit(0);
                    }
                    if (tCoordinates==false){
                        Console.println("Поле Coordinates не может быть Null");
                        System.exit(0);
                    }
                }
            }
        }catch (IdNotUnique exception){
            Console.printerror("Найден повторяющийся ID");
            System.exit(0);
        }catch (NumberFormatException exception){
            Console.println("Поле ID не может быть меньше, либо равно нулю");

        }
        catch (SecurityException exception) {
            Console.println("Файл защищен от чтения");
            System.exit(0);
        }catch (FileNotFoundException exception){
            Console.println("Файл защищен от чтения");
        }
        try{
            if (!file.canWrite()) throw new SecurityException();
        }catch (SecurityException exception){
            Console.println("Файл защищен от записи");
        }

        return spaceMarineLinkedList;
    }

    /**
     * Сохраняет коллекцию в файл
     * @param marineCollection коллекция которую мы подаем для сохранения в файл
     */
    public void unparse(LinkedList<SpaceMarine> marineCollection) {
        try (FileOutputStream fout = new FileOutputStream(file)) {
            byte[] buffer = "<?xml version=\"1.0\"?>\n".getBytes();
            fout.write(buffer, 0, buffer.length);
            buffer = "<SpaceMarines>\n".getBytes();
            fout.write(buffer, 0, buffer.length);
            for (SpaceMarine spaceMarine : marineCollection) {
                buffer = "\t<SpaceMarine>\n".getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<id>" + spaceMarine.getId() + "</id>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<name>" + spaceMarine.getName() + "</name>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<coordinates>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t\t<x>" + spaceMarine.getCoordinates().getX() + "<x>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t\t<y>" + spaceMarine.getCoordinates().getY() + "<y>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t</coordinates>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<creationDate>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t\t<year>" + spaceMarine.getCreationDate().getYear() + "</year>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t\t<month>" + spaceMarine.getCreationDate().getMonthValue() + "</month>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t\t<dayOfMonth>" + spaceMarine.getCreationDate().getDayOfMonth() + "</dayOfMonth>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t</creationDate>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<health>" + spaceMarine.getHealth() + "</health>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<height>" + spaceMarine.getHeight() + "</height>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<category>" + spaceMarine.getCategory() + "</category>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<weaponType>" + spaceMarine.getWeaponType() + "</weaponType>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<chapter>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<name>" + spaceMarine.getChapter().getName() + "</name>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<parentLegion>" + spaceMarine.getChapter().getParentLegion() + "</parentLegion>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<marinesCount>" + spaceMarine.getChapter().getMarinesCount() + "</marinesCount>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t<world>" + spaceMarine.getChapter().getWorld() + "</world>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = ("\t\t</chapter>\n").getBytes();
                fout.write(buffer, 0, buffer.length);
                buffer = "\t</SpaceMarine>\n".getBytes();
                fout.write(buffer, 0, buffer.length);
            }
            buffer = "</SpaceMarines>\n".getBytes();
            fout.write(buffer, 0, buffer.length);
            Console.println("Коллекция успешно сохранена!");
        } catch (FileNotFoundException e) {
            Console.printerror("Файл не найден");
        } catch (IOException e) {
            Console.printerror("Проверьте файл");
        }
        try {
            if (!file.canWrite()) throw new SecurityException();
        }catch (SecurityException exception){
            Console.println("Файл защищен от записи");
        }

    }
}
