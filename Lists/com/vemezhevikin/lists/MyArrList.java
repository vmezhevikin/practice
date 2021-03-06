package com.vemezhevikin.lists;

import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrList<E> implements List<E>
{
	private static final int DEFAULT_CAPACITY = 10;
	private static final int DEFAULT_RESIZE_FACTOR = 2;

	private int listVersion = 0;

	private int capacity;
	private int resizeFactor;
	private int size;
	private Object[] values;

	public MyArrList()
	{
		this(DEFAULT_CAPACITY, DEFAULT_RESIZE_FACTOR);
	}

	public MyArrList(int newCapacity, int newResizeFactor)
	{
		this.capacity = newCapacity;
		this.resizeFactor = newResizeFactor;
		this.size = 0;
		this.values = new Object[capacity];
	}

	private void checkCapacity(int newCapacity)
	{
		if (newCapacity < capacity)
			return;

		if (newCapacity > capacity * resizeFactor)
			makeBiggerArray(newCapacity * resizeFactor);
		else
			makeBiggerArray(capacity * resizeFactor);
	}

	private void makeBiggerArray(int newCapacity)
	{
		capacity = newCapacity;
		Object[] newArray = new Object[capacity];

		clearLinks(newArray);
		for (int i = 0; i < size; i++)
			newArray[i] = values[i];
		clearLinks(values);

		values = newArray;
	}

	private void shiftPartRight(int index)
	{
		for (int i = size; i > index; i--)
			values[i] = values[i - 1];
		values[index] = null;
	}

	private void shiftPartLeft(int index)
	{
		for (int i = index; i < size - 1; i++)
			values[i] = values[i + 1];
		values[size - 1] = null;
	}

	private void clearLinks(Object[] obj)
	{
		for (int i = 0; i < obj.length; i++)
			obj[i] = null;
	}

	private void checkIndex(int index)
	{
		if (index < 0)
			throw new IndexOutOfBoundsException("index<0 (" + index + ")");
		if (index >= size)
			throw new IndexOutOfBoundsException("index>=size (" + index + ">=" + size + ")");
	}

	private void checkIndex(int start, int end)
	{
		if (start < 0)
			throw new IndexOutOfBoundsException("start<0 (" + start + ")");
		if (end >= size)
			throw new IndexOutOfBoundsException("end>=size (" + end + ">=" + size + ")");
		if (start > end)
			throw new IndexOutOfBoundsException("start>end (" + start + ">" + end + ")");
	}

	private void checkElement(E element)
	{
		if (element == null)
			throw new NullPointerException("List doesn't maintain null pointers (Element)");
	}
	
	private void checkObject(Object obj)
	{
		if (obj == null)
			throw new NullPointerException("List doesn't maintain null pointers (Object)");
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	@Override
	public boolean add(E element)
	{
		checkElement(element);
		checkCapacity(size + 1);

		values[size] = element;
		size++;
		listVersion++;
		return true;
	}

	@Override
	public void add(int index, E element)
	{
		checkIndex(index);
		checkElement(element);
		checkCapacity(size + 1);

		shiftPartRight(index);
		values[index] = element;
		size++;
		listVersion++;
	}

	@Override
	public void clear()
	{
		clearLinks(values);
		size = 0;
		listVersion++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index)
	{
		checkIndex(index);

		Object oldValue = values[index];
		values[index] = null;
		shiftPartLeft(index);
		size--;
		listVersion++;
		return (E) oldValue;
	}

	@Override
	public boolean remove(Object obj)
	{
		checkObject(obj);

		int index = indexOf(obj);
		if (index == -1)
			return false;

		values[index] = null;
		shiftPartLeft(index);
		size--;
		listVersion++;
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E element)
	{
		checkIndex(index);
		checkElement(element);

		Object oldValue = values[index];
		values[index] = element;
		listVersion++;
		return (E) oldValue;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index)
	{
		checkIndex(index);
		listVersion++;
		return (E) values[index];
	}

	@Override
	public int indexOf(Object obj)
	{
		checkObject(obj);

		for (int i = 0; i < size; i++)
			if (values[i].equals(obj))
				return i;

		return -1;
	}

	@Override
	public int lastIndexOf(Object obj)
	{
		checkObject(obj);

		int index = -1;
		for (int i = 0; i < size; i++)
			if (values[i].equals(obj))
				index = i;

		return index;
	}

	@Override
	public boolean contains(Object obj)
	{
		checkObject(obj);
		return indexOf(obj) != -1;
	}

	@Override
	public Object[] toArray()
	{
		Object[] array = new Object[size];

		for (int i = 0; i < size; i++)
			array[i] = values[i];

		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a)
	{
		Object[] array = new Object[size];

		for (int i = 0; i < size; i++)
			array[i] = values[i];

		return (T[]) array;
	}

	@Override
	public boolean containsAll(Collection<?> col)
	{
		Iterator<?> iterator = col.iterator();
		while (iterator.hasNext())
			if (!contains(iterator.next()))
				return false;

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends E> col)
	{
		Iterator<?> iterator = col.iterator();
		while (iterator.hasNext())
			add((E) iterator.next());

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(int index, Collection<? extends E> col)
	{
		int ind = index;
		Iterator<?> iterator = col.iterator();
		while (iterator.hasNext())
		{
			add(ind, (E) iterator.next());
			ind++;
		}

		return true;
	}

	@Override
	public boolean removeAll(Collection<?> col)
	{
		boolean result = false;

		Iterator<?> iterator = col.iterator();
		while (iterator.hasNext())
		{
			Object obj = iterator.next();
			int ind = indexOf(obj);
			while (ind != -1)
			{
				remove(ind);
				result = true;
				ind = indexOf(obj);
			}
		}

		return result;
	}

	@Override
	public boolean retainAll(Collection<?> col)
	{
		boolean result = false;

		for (int i = size - 1; i >= 0; i--)
			if (!col.contains(values[i]))
			{
				remove(i);
				result = true;
			}

		return result;
	}

	@Override
	public Iterator<E> iterator()
	{
		return new MyIterator();
	}

	@Override
	public ListIterator<E> listIterator()
	{
		return new MyListIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index)
	{
		return new MyListIterator(index);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> subList(int fromIndex, int toIndex)
	{
		checkIndex(fromIndex, toIndex);
		MyArrList<E> subList = new MyArrList<>();

		for (int i = fromIndex; i <= toIndex; i++)
			subList.add((E) values[i]);

		return subList;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + size;
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyArrList<E> other = (MyArrList<E>) obj;
		if (capacity != other.capacity)
			return false;
		if (resizeFactor != other.resizeFactor)
			return false;
		if (size != other.size)
			return false;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		String result = "[ ";
		for (int i = 0; i < size; i++)
			result += values[i] + " ";
		result += "]";
		return result;
	}

	private class MyIterator implements Iterator<E>
	{
		protected int iteratorVersion;

		protected int currentIndex;
		protected int lastReturnedIndex;

		public MyIterator()
		{
			this(0);
		}

		private MyIterator(int index)
		{
			this.currentIndex = index;
			this.lastReturnedIndex = -1;
			this.iteratorVersion = listVersion;
		}

		protected void checkVersion()
		{
			if (iteratorVersion != listVersion)
				throw new ConcurrentModificationException("List has been modified");
		}

		protected void checkLastReturnedIndex()
		{
			if (lastReturnedIndex == -1)
				throw new IllegalStateException();
		}

		protected boolean inBounds(int index)
		{
			if (index < 0 || index >= size)
				return false;
			else
				return true;
		}

		@Override
		public boolean hasNext()
		{
			checkVersion();
			return inBounds(currentIndex);
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next()
		{
			checkVersion();
			checkIndex(currentIndex);
			lastReturnedIndex = currentIndex;
			currentIndex++;
			return (E) values[lastReturnedIndex];
		}

		@Override
		public void remove()
		{
			checkVersion();
			checkLastReturnedIndex();
			MyArrList.this.remove(lastReturnedIndex);
			currentIndex = lastReturnedIndex;
			lastReturnedIndex = -1;
			iteratorVersion = listVersion;
		}
	}

	private class MyListIterator extends MyIterator implements ListIterator<E>
	{

		public MyListIterator()
		{
			this(0);
		}

		public MyListIterator(int index)
		{
			super(index);
		}

		@Override
		public boolean hasPrevious()
		{
			checkVersion();
			return inBounds(currentIndex - 1);
		}

		@SuppressWarnings("unchecked")
		@Override
		public E previous()
		{
			checkVersion();
			checkIndex(currentIndex-1);
			currentIndex--;
			lastReturnedIndex = currentIndex;
			return (E) values[currentIndex];
		}

		@Override
		public void add(E element)
		{
			checkVersion();
			checkLastReturnedIndex();
			MyArrList.this.add(lastReturnedIndex, element);
			lastReturnedIndex = -1;
			iteratorVersion = listVersion;
		}

		@Override
		public void set(E element)
		{
			checkVersion();
			checkLastReturnedIndex();
			MyArrList.this.set(lastReturnedIndex, element);
			lastReturnedIndex = -1;
			iteratorVersion = listVersion;
		}

		@Override
		public int nextIndex()
		{
			return currentIndex;
		}

		@Override
		public int previousIndex()
		{
			return currentIndex - 1;
		}
	}
}
