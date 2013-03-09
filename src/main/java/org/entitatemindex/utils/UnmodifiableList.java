package org.entitatemindex.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class UnmodifiableList<E> extends ArrayList<E> {

    public UnmodifiableList(Collection<? extends E> c) {
        super(c);
    }
    
    public UnmodifiableList() {
        super();
    }

    
    @Override
    public Iterator<E> iterator() {
        return super.iterator();
    }

    @Override
    public boolean add(final Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(final Collection<? extends E> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(final Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(final Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(final Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return listIterator();
    }

    @Override
    public ListIterator<E> listIterator(final int index) {
        return listIterator(index);
    }

    @Override
    public void add(final int index, final E object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends E> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(final int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E set(final int index, final E object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(final int fromIndex, final int toIndex) {
        return subList(fromIndex, toIndex);
    }
}
