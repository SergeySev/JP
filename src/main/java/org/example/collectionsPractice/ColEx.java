package org.example.collectionsPractice;

import java.util.*;

/*
Для тех, кто хочет попрактиковаться немного с коллекциями, можно сделать следующие упражнения:
1. Напишите метод, который получает в качестве аргумента список строк и возвращает этот список без дубликатов✅
2. Напишите метод, который получает список строк и выведет в консоль количество вхождений каждого слова.✅
3. Создайте класс словаря (один язык -> другой язык), англо - немецкий.✅
Не надо для этого доставать данные каких-то реальных словарей, для теста просто добавьте несколько записей самостоятельно.✅
- В словаре должны содержаться слова на первом языке и набор возможных переводов на второй язык✅
- Создать метод get, получающий слово на первом языке и возвращающий все возможные переводы на второй✅
- Создать метод add, получающий слово и его перевод и добавляющий их в словарь.✅
Обратите внимание, что возможных переводов может быть несколько,
так что, если такое слово уже есть в словаре с другим переводом, то новый должен быть добавлен,
а не должен перезаписать имеющийся.
Также желательно пресечь добавление уже существующего перевода
- Создать метод remove, который может удалить один определенный перевод✅
для заданного слова (не все возможные)
- В отдельном классе с методом main создать ваш словарь, добавить немного данных и проверить работу методов.
 */
public class ColEx {
    public static Set<String> method1(List<String> list) {
        return new HashSet<>(list);
    }

    //  string, dungeon, dragon, string, dungeon, list
    public static Map<String, Integer> method2(List<String> list) {
        Map<String, Integer> result = new TreeMap<>();
        int count;
        for (int i = 0; i < list.size(); i++) {
            count = 1;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    count++;
                    list.remove(j);
                }
            }
            result.put(list.get(i), count);
        }
        return result;
    }

    public static void main(String[] args) {

        List<String> list = new ArrayList<>(List.of(new String[]{"string", "dungeon", "dragon", "string", "dungeon", "list"}));
        System.out.println(method2(list));
        System.out.println(method1(list));

        Dict dict = new Dict();
        System.out.println(dict.get("gut"));
        System.out.println(dict.dict);

        dict.add("gut", new HashSet<>(List.of("not bad")));
        dict.add("glückliche", new HashSet<>(List.of("happiness", "ecstasy")));
        System.out.println(dict.dict);

        dict.remove("gu", "fine");
        System.out.println(dict.dict);
        dict.remove("gut", "fine");
        System.out.println(dict.dict);
    }
}
