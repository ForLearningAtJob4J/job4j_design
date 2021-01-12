package ru.job4j.ocp;

import java.util.List;

/**
 * Если в дальнейшем понадобится подсчет слов для некоего другого вида файлов - DocFile, и в программе есть вызовы
 * getWordsCountInList, то придется изменять метод и его сигнатуру, и если этот метод кем-то вызывается, то этот
 * вызывающий метод необходимо будет также изменить
 *
 * */
public class BadFileWrapper {

    public int getWordsCountInList(List<TxtFile> files) {
        int total = 0;
        for (TxtFile file: files) {
            total += file.getWordsCount();
        }
        return  total;
    }
}

class TxtFile {
    int getWordsCount() {
        int res = 0;
        //получение массива слов файла
        return res;
    }
}