package edu.cynanthus.common.resource;

import edu.cynanthus.common.json.JsonProvider;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * El tipo Json file acces object.
 *
 * @param <T> el parámetro de tipo
 */
public class JsonFileAccesObject<T> implements ResourceAccesObject<File, T> {

    /**
     * El Type.
     */
    private final Type type;
    /**
     * El Fao.
     */
    private final FileAccesObject<Reader> fao;

    /**
     * Instancia un nuevo Json file acces object.
     *
     * @param type el type
     */
    public JsonFileAccesObject(Type type) {
        this.type = Objects.requireNonNull(type);
        this.fao = FileAccesObject.READER_FAO;
    }

    /**
     * Create.
     *
     * @param file     el file
     * @param resource el resource
     * @throws ResourceException el resource exception
     */
    @Override
    public void create(File file, T resource) throws ResourceException {
        try (Reader reader = new StringReader(JsonProvider.toJson(resource))) {
            fao.create(file, reader);
        } catch (IOException e) {
            throw new ResourceException(e, ResourceAction.CREATE);
        }
    }

    /**
     * Delete.
     *
     * @param file el file
     * @throws ResourceException el resource exception
     */
    @Override
    public void delete(File file) throws ResourceException {
        fao.delete(file);
    }

    /**
     * Read t.
     *
     * @param file el file
     * @return el t
     * @throws ResourceException el resource exception
     */
    @Override
    public T read(File file) throws ResourceException {
        return JsonProvider.fromJson(fao.read(file), type);
    }

    /**
     * Update.
     *
     * @param file     el file
     * @param resource el resource
     * @throws ResourceException el resource exception
     */
    @Override
    public void update(File file, T resource) throws ResourceException {
        try (Reader reader = new StringReader(JsonProvider.toJson(resource))) {
            fao.update(file, reader);
        } catch (IOException e) {
            throw new ResourceException(e, ResourceAction.CREATE);
        }
    }

}
