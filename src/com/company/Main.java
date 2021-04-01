package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    /**
     * Homework #5
     *
     * @author Bukhtaev Vladislav
     * <p>
     * Задание:
     * </p>
     * Посчитать количество пробелов в строке, используя метод
     * <p>
     * {@code Matcher.match(String str, String character)}
     * <p>
     * Метод возвращает {@code true} в случае обнаружения в строке {@code str} символа {@code character}.
     * <p>
     * Параметры:
     * <ul><li>str – строка в которой ищется символ
     * <li>character – искомый символ
     */

    private static final String TEMPLATE = " ";

    // Входная строка на 6600+ символов и 1000 пробелов.
    private static final String INPUT_STRING =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer blandit, purus ac elementum condimentum, nisi lorem interdum metus, ut aliquam diam orci vitae eros. Fusce fermentum ipsum vitae ante fermentum, vitae mattis quam rutrum. Nam tincidunt magna nec dictum condimentum. Etiam mattis congue nibh, vitae consequat enim euismod eu. Etiam placerat lacinia dui, non rhoncus leo cursus ac. Cras non metus justo. Curabitur scelerisque egestas semper. Donec eleifend vestibulum massa nec hendrerit. Cras nec metus bibendum, ultrices libero vel, aliquam odio. Ut pharetra congue neque, vestibulum tincidunt ligula gravida nec. Fusce ac ex sed ipsum porta rhoncus. Morbi ac turpis tempus, maximus nulla non, vulputate neque.\n" +
            "Fusce ut mauris maximus, interdum urna quis, ultrices libero. Vivamus vel bibendum ex. Praesent iaculis ut tellus sed efficitur. Cras in quam placerat, sollicitudin turpis vel, aliquet lorem. Nam luctus mi sed commodo condimentum. Nunc nec nulla vehicula neque dapibus suscipit quis sit amet leo. Duis sed metus cursus, vehicula augue at, egestas elit. Fusce vel aliquet ante. In hac habitasse platea dictumst. Morbi id turpis erat. Donec ullamcorper elit nibh, eget accumsan diam rutrum eu. Duis ac ex iaculis, tincidunt elit vel, hendrerit tortor. Sed lacus nisl, suscipit ac sollicitudin ac, commodo eget ex.\n" +
            "In pellentesque sem a cons equat phare tra. Phasellus ultrices lectus dolor, non porta leo egestas in. Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras ornare augue ligula. Aliquam vel ipsum risus. Nulla gravida vitae ex et tempor. Suspendisse a ligula augue.\n" +
            "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Aenean quis auctor eros, sed dictum lectus. Sed sed elit at enim ultricies volutpat vitae nec nisi. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed congue dapibus fermentum. Ut cursus malesuada varius. Quisque cursus pharetra nulla vel pharetra. Aenean a libero ac neque elementum tristique ac at justo. Nulla suscipit odio ut ipsum placerat, laoreet viverra leo porta.\n" +
            "Proin tincidunt finibus diam, ac males uada quam ullamcorper nec. Nulla varius eros fringilla, finibus ipsum et, consequat mi. Etiam condimentum massa vitae felis ullamcorper, vel finibus mi fermentum. Morbi vitae tristique felis. Aliquam turpis odio, finibus eu volutpat ac, lacinia et felis. Nulla ac enim fringilla, iaculis mi porta, aliquam risus. Aenean mattis posuere dui non vulputate. Quisque lacus augue, porta vitae vulputate ac, pulvinar condimentum metus. Nam sit amet dui at urna gravida bibendum. Aliquam vehicula, felis at tristique facilisis, metus nisi congue dui, in feugiat orci lectus at mi. Suspendisse ac ligula consequat, viverra enim nec, consectetur erat. Etiam ut odio placerat, fermentum odio et, semper diam. Quisque nec odio vestibulum, porta nisl vestibulum, pulvinar ex.\n" +
            "Quisque id purus mattis, aliquam ante eu, feu giat mau ris. Proin id libero tristique, mattis quam sit amet, lobortis velit. Morbi iaculis maximus massa, sed mollis metus tempor id. Quisque leo eros, posuere in massa ut, commodo fermentum nulla. Integer commodo, elit a fringilla auctor, dolor neque efficitur ligula, in cursus lorem eros eget sem. Vestibulum iaculis varius velit, et vulputate erat ullamcorper nec. Proin in arcu diam. Integer ac accumsan augue. Praesent ultrices lacinia erat vitae venenatis.\n" +
            "Phasellus posuere egestas est at tempus. Donec ornare tellus nec massa posuere, sit amet sollicitudin orci viverra. Vestibulum a convallis sem. Curabitur eu nibh luctus sapien fermentum viverra et ac urna. Integer ac mi arcu. Quisque at mi nec nibh sodales facilisis. Quisque eu eros volutpat lectus aliquam efficitur. Sed congue velit et turpis commodo, non tempor sem lacinia. Nunc efficitur orci quis velit ultrices hendrerit. Etiam non risus sed ante dictum posuere. Suspendisse in mauris imperdiet massa varius imperdiet. Fusce turpis ligula, sollicitudin eu orci in, lacinia commodo odio. Nullam ac iaculis urna. Duis vitae pellentesque massa, nec condimentum quam.\n" +
            "Quisque nisi orci, porttitor sit amet ex in, pulv inar conva llis enim. Fusce quis magna in odio sagittis mattis eget sit amet nisi. Duis quam purus, feugiat sed magna sed, venenatis congue augue. Mauris facilisis gravida libero. Integer id massa risus. Nam et molestie purus. Duis tincidunt nibh non sodales semper. Vivamus ut felis quam. Fusce eget fermentum enim. Cras id aliquet dolor. Maecenas ac scelerisque velit, sit amet mollis ipsum.\n" +
            "Proin mattis pretium magna eget elementum. Sed magna odio, soda les sit amet malesuada imperdiet, euismod in mi. Cras ex tellus, iaculis ac sodales eget, ullamcorper blandit augue. Curabitur ultrices metus quam, non ornare elit dapibus vel. Fusce mattis nec enim sed ornare. Donec viverra nibh in mi pellentesque, pellentesque aliquet nisi luctus. Aenean a ipsum hendrerit, varius odio sit amet, finibus eros. Sed ornare rhoncus nulla at semper.\n" +
            "Mauris nec nisi quis orci rutrum finibus. Proin vestibulum placerat nibh, in lacinia ligula rutrum id. Praesent ac mi a odio semper bibendum sed ac elit. Praesent vehicula felis sit amet erat rhoncus, et molestie nulla euismod. Proin sed turpis tincidunt nulla auctor pharetra. Sed et porttitor ex. Aliquam arcu lorem, lobortis vitae dui eget, semper facilisis elit. Nullam vel arcu dignissim, laoreet ligula ac, faucibus tortor. Nulla id velit quis ante ullamcorper facilisis. Integer congue, quam nec pretium vehicula, lacus dolor bibendum velit, nec varius nibh eros a augue. Etiam non condimentum massa, et tincidunt leo.\n" +
            "Vestibulum tris tique conv allis mollis. Vestibulum vel ante sed ante consequat feugiat quis sagittis justo. Fusce varius quam sit amet orci dictum, nec tincidunt urna tristique. Nulla rutrum luctus sem id commodo. Ut vitae arcu porttitor erat molestie ultricies. Duis pretium hendrerit tellus sit amet eleifend. Cras facilisis vitae eros ut malesuada. Quisque placerat sapien non nisl bibendum, id facilisis orci consequat. Nullam faucibus in risus eu imperdiet. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Maecenas eget scelerisque dui. Aliquam efficitur, lorem non fermentum volutpat, tellus quam porta sapien, nec venenatis mi neque tincidunt enim. Duis eu velit aliquet, tincidunt elit sit amet, fringilla dolor. Phasellus posuere lacus nec nisi consectetur, ac viverra enim suscipit. Sed sed convallis ex, sed aliquet urna.\n" +
            "Proin non massa id risus ultricies ultricie s elementum at tellus. Pellentesque magna nulla, condimen tum non ultricies non, auctor in lorem. Ut sit amet mi odio. Curabitur."; // 11

    private static int sum = 0;

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        final long startTime = System.currentTimeMillis();


        int subStringLength = 4; // Длина подстроки.

        // Массив индексов первого символа для каждой подстроки.
        int[] pointers = getPointers(INPUT_STRING, subStringLength);

        ExecutorService executor = Executors.newCachedThreadPool();

        // Передаем отдельную задачу для каждой подстроки исходной строки в thread pool.
        for (int pointer : pointers) {
            executor.submit(new Runnable() {
                @Override
                public void run() {

                    String currentString = INPUT_STRING.substring(pointer, pointer + subStringLength);

                    for (int i = 0; i < currentString.length(); i++) {
                        if (Matcher.match(String.valueOf(currentString.charAt(i)), TEMPLATE)) {
                            lock.lock();
                            sum++;
                            lock.unlock();
                        }
                    }
                }
            });
        }

        executor.shutdown();

        try {

            if (executor.awaitTermination(3, TimeUnit.MINUTES)) {
                System.out.println("Count of space: " + sum);
                final long takenTime = System.currentTimeMillis() - startTime;
                System.out.println("It took " + takenTime + " milliseconds.");
            } else {
                System.out.println("Time is out! 3 minutes have passed.");
            }

        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static int[] getPointers(String string, int substringLength) {

        int size = (int) Math.ceil((double) string.length() / (double) substringLength);
        int[] pointers = new int[size];

        for (int i = 0; i < pointers.length; i++) {
            pointers[i] = substringLength * i;
        }

        return pointers;
    }
}