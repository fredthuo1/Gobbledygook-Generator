import java.io.*;
import java.util.*;

public class Generator {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file name: ");
        String fileName = scanner.nextLine();
        try {
            returnMap( fileName );
        } catch ( FileNotFoundException e ) {
            System.out.println( "Error file not found!" );
            e.printStackTrace();
        }
    }

    public static Map returnMap( String file ) throws FileNotFoundException {

        File fileName = new File( file );
        Map<String, Integer> map = new TreeMap<>();
        String data = "";

            BufferedReader bufferedReader = null;
            try {

                bufferedReader = new BufferedReader( new FileReader( fileName ));
                String available;
                while ( (available = bufferedReader.readLine()) != null ) {
                   data = available;
                }
            } catch ( FileNotFoundException e ) {
                e.printStackTrace();
            } catch ( IOException e ) {
                e.printStackTrace();
                System.out.println( "File not found!!" );
            } finally {
                if ( bufferedReader != null ) {
                    try {
                        bufferedReader.close();
                    } catch ( IOException e ) {
                        e.printStackTrace();
                    }
                }
            }

        System.out.println( data );

        String[] words = data.split(" " );

        for ( int i = 0; i < words.length; i++ ) {
            String key = words[i].toLowerCase();


                if ( !map.containsKey( key ) ) {
                    map.put( key, 1 );
                }
                else {
                    int value = map.get( key );
                    value++;
                    map.put( key, value );
                }

        }

        List<Integer> keyList = new ArrayList( map.keySet() );

        List<Integer> valueList = new ArrayList( map.values() );

        for ( int i = 1; i < valueList.size() - 1; i++ ) {
            valueList.set( i, Integer.valueOf(valueList.get(i)) +
            Integer.valueOf(valueList.get(i - 1)) );
        }
        Random random = new Random();
        valueList.remove( valueList.size() - 1 );

        int low = 1;
        int high = valueList.get( valueList.size() - 1 );

        Object[] keys = map.values().toArray();
        ArrayList<Object> jibrish = new ArrayList<>();
        for ( int i  = 0; i < keyList.size() - 1; i++ ) {
            int ran = random.nextInt( high - low ) + low;
            if ( valueList.get(i) >= ran  ) {
                jibrish.add( keyList.get(i));
            }

        }

        System.out.println( map.toString() );

        System.out.println( jibrish.toString() );

        return map;
    }

}
