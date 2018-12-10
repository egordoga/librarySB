package ua.lib_sb.model;

public class RussianLetter {
    private final static String[] RUS_LETS = {"А", "Б", "В", "Г", "Д", "Е", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О",
            "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я"};

    public static String[] getRusLets() {
        return RUS_LETS;
    }
}
