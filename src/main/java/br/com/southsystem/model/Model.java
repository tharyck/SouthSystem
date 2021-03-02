package br.com.southsystem.model;

/**
 * Interface to Generic Service. All models must implement it.
 *
 * @param <ID>
 */
public interface Model<ID> {
    public ID getId();
    public void setId(ID id);
}
