package homeworks;

public class ArrayHomework {
    public static void main(String[] args) {
        int[] array = {5, 4, 7, 6, 8, 1, 2, 21, 6, 5, 7};
        for (int x : array){
            System.out.print(x + " ");
        }
        System.out.println();
    // int n = 6;

    // int count = 0;
    // for(int x: array) {
    //     if (x == n){
    //         count++;
    //     }
    // }
    // System.out.println("count:" + count);


        //OPTION 1
     // int[] reversedArray = new int[array.length];
     // int j = 0;
     //   for (int i = array.length-1; i >= 0; i--) {
     //       reversedArray[j++] = array[i];
     //   }
     //   for (int x : reversedArray) {
     //       System.out.print(x + " ");
     //   }

        //OPTION 2
       // int startIndex = 0;
       // int endIndex = array.length - 1;
      // for (int i = 0; i < array.length / 2; i++) {
      //     int tmp = array[i];
      //
      // }
      // for (int x : array){
      //     System.out.print(x + " ");
      // }

        //OPTION 3
      //  while (startIndex < endIndex){
      //      int tmp = array[startIndex];
      //      array[startIndex++ ] = array[endIndex];
      //      array[endIndex--] = tmp;
      //  }
      //  for (int x : array){
      //      System.out.print(x + " ");
      //  }

       // int duplicateCount = 0;
       // int[] duplicateArray = new int[array.length];
       // int duplicateArrayIndex = 0;
       // for (int i = 0; i < array.length; i++) {
       //     for (int j = i + 1; j < array.length; j++) {
       //         if (array[i] == array[j]){
       //             boolean existDuplicate = false;
       //             for (int x : duplicateArray) {
       //                 if (array[i] == x) {
       //                     existDuplicate = true;
       //                     break;
       //                 }
       //             }
       //             if(!existDuplicate){
       //                 duplicateCount++;
       //                 duplicateArray[duplicateArrayIndex++] = array[i];
       //                 break;
       //             }
//
       //         }
       //     }
       // }
       // System.out.println("duplicateCount: " + duplicateCount);

      // char[] chars = {'բ', 'ա', 'ր', 'և', 'ա', 'շ', 'խ', 'ա', 'ր', 'հ'};
      // char[] vowels = {'ա', 'ե', 'ը', 'է', 'ի', 'ո', 'օ'};
      // int vowelsCount = 0;
      // for (char c : chars) {
      //     for (char v : vowels) {
      //         if (c == v){
      //             vowelsCount++;
      //             break;
      //         }
      //     }
      // }
      // System.out.println("vowelsCount: " + vowelsCount);

    }
}
