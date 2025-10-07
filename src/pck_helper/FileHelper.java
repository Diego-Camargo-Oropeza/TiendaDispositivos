package pck_helper;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class FileHelper<T extends Serializable> {

    private final Path path;

    public FileHelper(Path path) {
        this.path = path;
    }

    /**
     * Si no existe el archivo, lo crea con esta semilla. Solo se ejecuta 1 vez.
     */
    public void seedIfAbsent(List<T> seed) {
        if (Files.exists(path)) {
            return;
        }
        saveAll(seed);
    }

    /**
     * Carga todo; si no existe, regresa lista vacía.
     */
    public List<T> loadAll() {
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            Object o = ois.readObject();
            return (List<T>) o;
        } catch (EOFException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error leyendo " + path, e);
        }
    }

    /**
     * Guarda la colección completa (movimiento atómico, bien para Windows).
     */
    public void saveAll(Collection<T> data) {
        try {
            Path dir = path.getParent();
            if (dir != null) {
                Files.createDirectories(dir);
            }
            Path tmp = Paths.get(path.toString() + ".tmp");
            try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(tmp))) {
                oos.writeObject(new ArrayList<>(data));
            }
            Files.move(tmp, path,
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            throw new RuntimeException("Error escribiendo " + path, e);
        }
    }

    /**
     * Agrega un elemento y persiste.
     */
    public void add(T item) {
        List<T> all = loadAll();
        all.add(item);
        saveAll(all);
    }
}
