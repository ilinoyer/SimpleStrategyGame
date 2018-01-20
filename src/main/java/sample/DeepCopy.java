package sample;

import java.io.*;

public class DeepCopy {

        public static Object copy(Object orig) {
            Object obj = null;
            try {

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(bos);
                out.writeObject(orig);
                out.flush();
                out.close();

                ObjectInputStream in = new ObjectInputStream(
                        new ByteArrayInputStream(bos.toByteArray()));
                obj = in.readObject();
            }
            catch(IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return obj;
        }


}
