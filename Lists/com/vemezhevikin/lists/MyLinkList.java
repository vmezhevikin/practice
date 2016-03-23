package com.vemezhevikin.lists;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;

public class MyLinkList<E> implements List<E>
{
	private int listVersion = 0;
	
	private int size;
	private MyNode<E> first;
	private MyNode<E> last;

	public MyLinkList()
	{
		first = null;
		last = null;
		size = 0;
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
	
	private void checkValue(E value)
	{
		if (value == null)
			throw new NullPointerException("List doesn't maintain null pointers (Value)");
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
	public boolean add(E value)
	{
		checkValue(value);		
		linkLast(value);
		return true;
	}
	
	@Override
	public void add(int index, E value)
	{
		checkIndex(index);
		checkValue(value);		
		linkIndex(index, value);
	}
	
	private void linkFirst(E value)
	{
		MyNode<E> newElement = new MyNode<>(value);

		if (size == 0)
		{
			first = newElement;
			last = newElement;
		} else
		{
			first.prev = newElement;
			newElement.next = first;
			first = newElement;
		}
		
		listVersion++;
		size++;
	}
	
	private void linkLast(E value)
	{
		MyNode<E> newElement = new MyNode<>(value);

		if (size == 0)
		{
			first = newElement;
			last = newElement;
		} else
		{
			last.next = newElement;
			newElement.prev = last;
			last = newElement;
		}
		
		listVersion++;
		size++;
	}
	
	private void linkIndex(int index, E value)
	{
		if (index == size)
			linkLast(value);
		
		if (index == 0)
			linkFirst(value);
		
		MyNode<E> newElement = first;
		for (int i = 0; i < index; i++)
			newElement = newElement.next;
		
		linkBefore(newElement, value);
	}
	
	private void linkBefore(MyNode<E> nextElement, E value)
	{
		MyNode<E> newElement = new MyNode<>(value);
		MyNode<E> prevElement = nextElement.prev;
		
		prevElement.next = newElement;
		newElement.prev = prevElement;
		newElement.next = nextElement;
		nextElement.prev = newElement;
		
		listVersion++;
		size++;
	}

	@Override
	public E get(int index)
	{
		checkIndex(index);
		
		MyNode<E> element = first;
		for (int i = 0; i < index; i++)
			element = element.next;

		listVersion++;
		return element.value;
	}

	@Override
	public E set(int index, E value)
	{
		checkIndex(index);
		checkValue(value);
		
		MyNode<E> element = first;
		for (int i = 0; i < index; i++)
			element = element.next;

		E oldElementValue = element.value;
		element.value = value;
		listVersion++;
		return oldElementValue;
	}

	@Override
	public E remove(int index)
	{
		checkIndex(index);

		MyNode<E> element = first;
		for (int i = 0; i < index; i++)
			element = element.next;

		E oldElementValue = element.value;
		unlink(element);
		return oldElementValue;
	}
	
	@Override
	public boolean remove(Object obj)
	{
		checkObject(obj);

		for (MyNode<E> x = first; x != null; x = x.next)
			if (x.value.equals(obj))
			{
				unlink(x);
				return true;
			}

		return false;
	}
	
	private void unlink(MyNode<E> element)
	{
		if (element == first)
		{
			MyNode<E> nextElement = element.next;
			nextElement.prev = null;
			first = nextElement;
		} else if (element == last)
		{
			MyNode<E> prevElement = element.prev;
			prevElement.next = null;
			last = prevElement;
		} else
		{
			MyNode<E> prevElement = element.prev;
			MyNode<E> nextElement = element.next;
			prevElement.next = nextElement;
			nextElement.prev = prevElement;
		}
		element.value = null;
		size--;
		listVersion++;
	}

	@Override
	public int indexOf(Object obj)
	{
		checkObject(obj);

		MyNode<E> element = first;
		for (int i = 0; i < size; i++)
		{
			if (element.value.equals(obj))
				return i;
			element = element.next;
		}
		
		return -1;
	}

	@Override
	public int lastIndexOf(Object obj)
	{
		checkObject(obj);
		
		int seekIndex = -1;

		MyNode<E> element = first;
		for (int i = 0; i < size; i++)
		{
			if (element.value.equals(obj))
				seekIndex = i;
			element = element.next;
		}
		
		return seekIndex;
	}

	@Override
	public boolean contains(Object obj)
	{
		return indexOf(obj) != -1;
	}

	@Override
	public Object[] toArray()
	{
		Object[] array = new Object[size];
		MyNode<E> element = first;
		for (int i = 0; i < size; i++)
		{
			array[i] = element.value;
			element = element.next;
		}
		
		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a)
	{
		Object[] array = new Object[size];
		MyNode<E> element = first;
		for (int i = 0; i < size; i++)
		{
			array[i] = element.value;
			element = element.next;
		}
		
		return (T[]) array;
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
	public boolean containsAll(Collection<?> col)
	{
		Iterator<?> iterator = col.iterator();
		while (iterator.hasNext())
			if(!contains(iterator.next()))
				return false;
		
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> col)
	{
		boolean result = false;

		MyNode<E> element;
		MyNode<E> nextElement;
		
		for(element = first; element != null;)
		{
			nextElement = element.next;
			if (col.contains(element.value))
			{
				unlink(element);
				result = true;
			}
			element = nextElement;
		}

		return result;
	}

	@Override
	public boolean retainAll(Collection<?> col)
	{
		boolean result = false;

		MyNode<E> element;
		MyNode<E> nextElement;
		
		for(element = first; element != null;)
		{
			nextElement = element.next;
			if (!col.contains(element.value))
			{
				unlink(element);
				result = true;
			}
			element = nextElement;
		}

		return result;
	}

	@Override
	public void clear()
	{
		MyNode<E> element;
		MyNode<E> nextElement;
		
		for (element = first; element != null;)
		{
			nextElement = element.next;
			element.prev = null;
			element.value = null;
			element.next = null;
			element = nextElement;
		}
		
		first = null;
		last = null;
		size = 0;
		listVersion++;
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

	@Override
	public List<E> subList(int fromIndex, int toIndex)
	{
		checkIndex(fromIndex, toIndex);
		MyLinkList<E> subList = new MyLinkList<>();
		
		MyNode<E> element = first;
		for (int i = 0; i < fromIndex; i++)
			element = element.next;
		for (int i = fromIndex; i <= toIndex; i++)
		{
			subList.add(element.value);
			element = element.next;
		}
		
		return subList;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		
		for(MyNode<E> element = first; element != null; element = element.next)
			result = prime * result + element.value.hashCode();
		
		result = prime * result + size;
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
		
		MyLinkList<E> otherList = (MyLinkList<E>) obj;
		if (size != otherList.size)
			return false;
		
		for(MyNode<E> element = first, otherElement = otherList.first; element != null; element = element.next, otherElement = otherElement.next)
			if (!element.value.equals(otherElement.value))
				return false;
		
		return true;
	}

	@Override
	public String toString()
	{
		String result = "[ ";
		for (MyNode<E> element = first; element != null; element = element.next)
			result += element.value + " ";
		result += "]";
		return result;
	}

	@SuppressWarnings("hiding")
	private class MyNode<E>
	{
		MyNode<E> prev;
		E value;
		MyNode<E> next;
		
		MyNode(E value)
		{
			this.prev = null;
			this.value = value;
			this.next = null;
		}	
	}
	
	private class MyIterator implements Iterator<E>
	{
		protected int iteratorVersion;

		protected int index;
		protected MyNode<E> current;
		protected MyNode<E> lastReturned;

		public MyIterator()
		{
			this(0);
		}

		private MyIterator(int index)
		{
			this.index = index;
			this.current = first;
			this.lastReturned = null;
			this.iteratorVersion = listVersion;
		}

		protected void checkVersion()
		{
			if (iteratorVersion != listVersion)
				throw new ConcurrentModificationException("List has been modified");
		}

		protected void checkLastReturned()
		{
			if (lastReturned == null)
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
			return inBounds(index);
		}

		@Override
		public E next()
		{
			checkVersion();
			checkIndex(index);
			
			if (current == null)
				current = first;
			lastReturned = current;
			current = current.next;
			index++;
			return (E) lastReturned.value;
		}

		@Override
		public void remove()
		{
			checkVersion();
			checkLastReturned();
			current = lastReturned.next;
			unlink(lastReturned);
			lastReturned = null;
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
			return inBounds(index - 1);
		}

		@Override
		public E previous()
		{
			checkVersion();
			checkIndex(index-1);
			
			if (current == null)
				current = last;
			else
				current = current.prev;
			lastReturned = current;
			index--;
			return (E) lastReturned.value;
		}

		@Override
		public void add(E value)
		{
			checkVersion();
			checkLastReturned();
			checkValue(value);
			
			linkBefore(lastReturned, value);
			lastReturned = null;
			iteratorVersion = listVersion;
		}
		
		@Override
		public void set(E value)
		{
			checkVersion();
			checkLastReturned();
			checkValue(value);
			
			lastReturned.value = value;
			lastReturned = null;
			listVersion++;
			iteratorVersion = listVersion;
		}
		
		@Override
		public int nextIndex()
		{
			return index;
		}

		@Override
		public int previousIndex()
		{
			return index - 1;
		}
	}
}
