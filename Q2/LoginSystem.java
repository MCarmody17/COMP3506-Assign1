

public class LoginSystem extends LoginSystemBase {
    LinearProbingHashTable login = new LinearProbingHashTable();

    @Override
    public int size() {
        return login.getMaxSize();
    }

    @Override
    public int getNumUsers() {
        /* Add your code here! */
        return login.getSize();
    }

    @Override
    public int hashCode(String key) {
        return login.hash(key);
    }

    @Override
    public boolean addUser(String email, String password) {
        login.insert(email, password);
        return true;
    }

    @Override
    public boolean removeUser(String email, String password) {
        login.remove(email);
        return true;
    }

    @Override
    public int checkPassword(String email, String password) {
        return login.checkPassword(email,password);
    }

    @Override
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        return login.changePassword(email, oldPassword, newPassword);
    }

    /* Add any extra functions below */
    /** Class LinearProbingHashTable **/
    class LinearProbingHashTable
    {
        private int currentSize, maxSize;
        private String[] keys;
        private String[] vals;

        /** Constructor **/
        public LinearProbingHashTable()
        {
            currentSize = 0;
            maxSize = 101;
            keys = new String[maxSize];
            vals = new String[maxSize];
        }
        public LinearProbingHashTable(int maxSize)
        {
            currentSize = 0;
            maxSize = maxSize;
            keys = new String[maxSize];
            vals = new String[maxSize];
        }


        /** Function to clear hash table **/
        public void makeEmpty()
        {
            currentSize = 0;
            keys = new String[maxSize];
            vals = new String[maxSize];
        }

        /** Function to get size of hash table **/
        public int getSize()
        {
            return currentSize;
        }

        public int getMaxSize(){
            return maxSize;
        }

        /** Function to check if hash table is full **/
        public boolean isFull()
        {
            return currentSize == maxSize;
        }

        /** Function to check if hash table is empty **/
        public boolean isEmpty()
        {
            return getSize() == 0;
        }

        /** Fucntion to check if hash table contains a key **/
        public boolean contains(String key)
        {
            return get(key) !=  null;
        }


        public int checkPassword(String email, String password) {
            if(contains(email)){
                String storedPassword = get(email);
                String verify = passwordHash(password);
                if(storedPassword.equals(verify)){
                    return hash(email);
                }
                return -2;
            }
            return -1;

        }

        private boolean changePassword(String email, String oldPassword, String newPassword){
            if(checkPassword(email,oldPassword) == 1){
                String newPass = passwordHash(newPassword);
                int k = hash(email);
                while (keys[k] != null)
                {
                    if (keys[k].equals(email)) {
                        vals[k] = newPass;
                        return true;
                    }
                    k = (k + 1) % maxSize;
                }
            }
            return false;
        }


        public void resize(){
            maxSize *= 3;
            LinearProbingHashTable nt = new LinearProbingHashTable(maxSize);
//add resize!!!
        }

        /** Function to get hash code of a given key **/
        private int hash(String key)
        {
            int c = 31;
            int hashcode = 0;
            for(int i = 0; i < key.length();i++){
                char ch = key.charAt(i);
                hashcode += (int) ch;
                if(i!= key.length()-1){
                    hashcode *= c;
                }

            }

            hashcode = Math.floorMod(hashcode,getMaxSize());
            return hashcode;
        }

        private String passwordHash(String password){
            int c = 31;
            int hashcode = 0;
            for(int i = 0; i < password.length();i++){
                char ch = password.charAt(i);
                hashcode += (int) ch;
                hashcode *= c;

            }
            String hashed = Integer.toString(hashcode);
            return hashed;
        }



        /** Function to insert key-value pair **/
        public void insert(String key, String val)
        {
            if(contains(key)){
                System.out.println("Already have that email");
                return;
            }
            int tmp = hash(key);
            String pass = passwordHash(val);

            int i = tmp;
            //if (isFull()) {
            //    resize();
            //}
            do
            {
                if (keys[i] == null)
                {
                    keys[i] = key;
                    vals[i] = pass;
                    currentSize++;
                    return;
                }
                if (keys[i].equals(key))
                {
                    vals[i] = pass;
                    return;
                }
                i = (i + 1) % maxSize;
            } while (i != tmp);
        }

        /** Function to get value for a given key **/
        public String get(String key)
        {
            int i = hash(key);
            while (keys[i] != null)
            {
                if (keys[i].equals(key))
                    return vals[i];
                i = (i + 1) % maxSize;
            }
            return null;
        }

        /** Function to remove key and its value **/
        public void remove(String key)
        {
            if (!contains(key))
                return;

            /** find position key and delete **/
            int i = hash(key);
            while (!key.equals(keys[i]))
                i = (i + 1) % maxSize;
            keys[i] = vals[i] = null;

            /** rehash all keys **/
            for (i = (i + 1) % maxSize; keys[i] != null; i = (i + 1) % maxSize)
            {
                String tmp1 = keys[i], tmp2 = vals[i];
                keys[i] = vals[i] = null;
                currentSize--;
                insert(tmp1, tmp2);
            }
            currentSize--;
        }

        /** Function to print HashTable **/
        public void printHashTable()
        {
            System.out.println("\nHash Table: ");
            for (int i = 0; i < maxSize; i++)
                if (keys[i] != null)
                    System.out.println(keys[i] +" "+ vals[i]);
            System.out.println();
        }
    }



        public static void main(String[] args) {
            /*
             * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
             * REMOVE THE MAIN METHOD BEFORE SUBMITTING TO THE AUTOGRADER
             * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
             * The following main method is provided for simple debugging only
             */
            LoginSystem loginSystem = new LoginSystem();
            System.out.println(loginSystem.hashCode("GQHTMP"));
            System.out.println(loginSystem.hashCode("H2HTN1"));
            assert loginSystem.hashCode("GQHTMP") == loginSystem.hashCode("H2HTN1");
            assert loginSystem.size() == 101;
            System.out.println(loginSystem.size());
            assert loginSystem.checkPassword("a@b.c", "L6ZS9") == -1;
            System.out.println(loginSystem.checkPassword("a@b.c", "L6ZS9"));
            loginSystem.addUser("a@b.c", "L6ZS9");

            assert loginSystem.checkPassword("a@b.c", "ZZZZZZ") == -2;
            System.out.println(loginSystem.checkPassword("a@b.c", "ZZZZZZ"));

            assert loginSystem.checkPassword("a@b.c", "L6ZS9") == 94;

            System.out.println(loginSystem.checkPassword("a@b.c", "L6ZS9"));

            loginSystem.removeUser("a@b.c", "L6ZS9");

            assert loginSystem.checkPassword("a@b.c", "L6ZS9") == -1;
        }
}
