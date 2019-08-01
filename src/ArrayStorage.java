/**
 * Array based storage for Resumes
 *
 * @version 0.1
 *
 * @autor Maksim P.
 */
public class ArrayStorage {

    /**
     * Numbers of entries in the array
     */
    private int countResume = 0;
    private final int SIZE_STORAGE = 10000;

    private Resume[] storage = new Resume[SIZE_STORAGE];

    /**
     * clear all record. all -> null
     * */
    void clear() {
        for (int i = 0; i < countResume; i++) {
            storage[i] = null;
        }
        countResume = 0;
    }

    /**
     * Save record.
     *
     * @param  resume updated record.
     * */
    void save(Resume resume) {
        if (resume != null) {
            if (countResume == SIZE_STORAGE) {
                System.out.println("Сохранить невозможно: хранилище переполнено!");
                return;
            }
            int index = search(resume.uuid);
            if (index != -1) {
                System.out.println("Запись с таким uuid уже существует!!!");
                return;
            }
            storage[countResume] = resume;
            countResume++;
            System.out.println(countResume);
        }
    }

    /**
     * update record
     *
     *  @param  resume updated record.
     * */
    void update (Resume resume){
        if (resume != null){
            int index = search(resume.uuid);
            if (index != -1){
                storage[index] = resume;
            }
        }
    }

    /**
     * Get record.
     *
     * @param   uuid Search parameter.
     *
     * @return  found record.
     * */
    public Resume get(String uuid) {
        int index = search(uuid);
        if (index != -1){
            return storage[index];
        }
        return null;
    }

    /**
     * Delete record
     *
     * @param   uuid    Delete parameter.
     * */
    void delete(String uuid) {
        int position = search(uuid);
        if (position != -1) {
            for (int i = position; i < countResume-1; i++) {
                storage[i] = storage[i + 1];
            }
            storage[countResume-1] = null;
            countResume--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[countResume];
        System.arraycopy(storage, 0, resumes, 0, countResume);
        return resumes;
    }

    /**
     * Size.
     *
     * @return  The number of entries in the array (Resume[] storage).
     **/
    int size() {
        return countResume;
    }

    /**
     * Search for an entry in an array (Resume[] storage).
     *
     * @param   uuid Search parameter.
     *
     * @return  Array position number or -1 if record not found.
     */
    int search(String uuid){
        int position = -1;
        for (int i = 0; i < countResume; i++) {
            if (storage[i].toString().equals(uuid)) {
                position = i;
                break;
            }
        }
        if (position == -1) {
            System.out.println("Resume (uuid: " + uuid + ") not found");
        }
        return position;
    }
}