
public class BinaryTree {

	private static class BinaryNode
	{
		public Object element;
		public BinaryNode left;
		public BinaryNode right;
		
		public BinaryNode (Object o)
		{
			this (o, null, null);
		}
		
		public BinaryNode (Object o, BinaryNode l, BinaryNode r)
		{
			element = o;
			left = l;
			right = r;
		}
		
		public String toString()
		{
			return "" + element;
		}
	}
	
	private BinaryNode root;
	
	public BinaryTree( BinaryNode root )
	{
		this.root = root;
	}
	
	public BinaryNode breadthFirstSearch( Object o )
	{
		BinaryNode b;
		QueueLi q = new QueueLi();
		q.enqueue (root);
		
		while(!q.isEmpty())
		{
			b = (BinaryNode)q.dequeue();
			
			if (b.element.equals (o))
				return b;
			
			if (b.left != null)
				q.enqueue (b.left);
			if (b.right != null)
				q.enqueue (b.right);
		}
		
		return null;
	}
}