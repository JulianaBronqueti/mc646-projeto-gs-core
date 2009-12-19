/*
 * This file is part of GraphStream.
 * 
 * GraphStream is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * GraphStream is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with GraphStream.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Copyright 2006 - 2009
 * 	Julien Baudry
 * 	Antoine Dutot
 * 	Yoann Pigné
 * 	Guilhelm Savin
 */

package org.graphstream.ui.graphicGraph;

import org.graphstream.graph.*;
import org.graphstream.ui.graphicGraph.stylesheet.Selector;
import org.graphstream.ui.graphicGraph.stylesheet.Selector.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Graphical node.
 * 
 * <p>
 * A graphic node defines a position (x,y,z), a string label, and a style from the style sheet.
 * </p>
 * 
 * @see GraphicGraph
 */
public class GraphicNode extends GraphicElement implements Node 
{
	/**
	 * The position of the node. In graph units.
	 */
	public float x, y, z;

	/**
	 * Node bounds in graph units and on the 2D screen (set by the rendering engine). This represents
	 * globaly the space used on screen. In graph units.
	 */
	public float boundsX, boundsY, boundsW, boundsH;
	
	/**
	 * New graphic node.
	 * @param id The node identifier.
	 * @param x The node X position.
	 * @param y The node Y position.
	 * @param z The node Z position.
	 * @param attributes The node attribute set (can be null).
	 */
	public GraphicNode( GraphicGraph graph, String id, Float x, Float y, Float z, HashMap<String, Object> attributes )
	{
		super( id, graph );
		
		this.x = x;
		this.y = y;
		this.z = z;
/*		
		addAttribute( "x", x );
		addAttribute( "y", y );
		addAttribute( "z", z );
*/		
		if( attributes != null )
		{
			addAttributes( attributes );
		}
	}

	@Override
    protected Type getSelectorType()
    {
	    return Selector.Type.NODE;
    }
	
	@Override
	public boolean contains( float x, float y )
	{
		return( x > boundsX && y > boundsY && x < ( boundsX + boundsW ) && y < ( boundsY + boundsH ) );
	}
	
	@Override
	public float getX()
	{
		return x; 
	}

	@Override
	public float getY()
	{
		return y;
	}
	
	@Override
	public float getZ()
	{
		return z;
	}
	
	@Override
    public void move( float x, float y, float z )
    {
    	this.x = x;
    	this.y = y;
    	this.z = z;
    	mygraph.graphChanged = true;
    }

	@Override
	protected void attributeChanged( String sourceId, long timeId, String attribute, AttributeChangeEvent event, Object oldValue, Object newValue )
	{
		super.attributeChanged( sourceId, timeId, attribute, event, oldValue, newValue );
		
		if( attribute.equals( "x" ) )
		{
			x = numberAttribute( newValue );
			mygraph.graphChanged = true;
		}
		else if( attribute.equals( "y" ) )
		{
			y = numberAttribute( newValue );
			mygraph.graphChanged = true;
		}
		else if( attribute.equals( "z" ) )
		{
			z = numberAttribute( newValue );
			mygraph.graphChanged = true;
		}
		else if( attribute.equals( "xy" ) || attribute.equals( "xyz" ) )
		{
			if( newValue instanceof Object[] )
			{
				Object numbers[] = (Object[])newValue;
				
				if( numbers.length > 1 )
				{
					x = numberAttribute( numbers[0] );
					y = numberAttribute( numbers[1] );
		
					if( numbers.length > 2 )
						z = numberAttribute( numbers[2] );
					
					mygraph.graphChanged = true;
				}
			}
		}
	}

	/**
	 * Try to convert the object to a float.
	 * @param value The object to convert.
	 * @return The value.
	 */
	protected float numberAttribute( Object value )
	{
		if( value instanceof Number )
		{
			return ((Number)value).floatValue();
		}
		else if( value instanceof CharSequence )
		{
			String xs = ((CharSequence)value).toString();
			
			try
			{
				return Float.parseFloat( xs );
			}
			catch( NumberFormatException e )
			{
			}
		}
		
		return 0;
	}
	
	@Override
	public void setBounds( float x, float y, float w, float h )
	{
		// We cannot consider the X/Y here, since this will trigger a redraw that will recompute
		// the graph overall width and height therefore move this sprite x /y, etc, etc..
		mygraph.graphChanged = (
				   ((int)(w*1000)) != ((int)(boundsW*1000))
				|| ((int)(h*1000)) != ((int)(boundsH*1000)) );
	
		boundsX = x;
		boundsY = y;
		boundsW = w;
		boundsH = h;
	}

// Node interface.
	
	/**
	 * Not implemented.
	 */
	public Iterator<Node> getBreadthFirstIterator()
    {
		throw new RuntimeException( "not implemented !" );
    }

	/**
	 * Not implemented.
	 */
	public Iterator<Node> getBreadthFirstIterator( boolean directed )
    {
		throw new RuntimeException( "not implemented !" );
    }

	/**
	 * Not implemented.
	 */
	public Iterator<Node> getDepthFirstIterator()
    {
		throw new RuntimeException( "not implemented !" );
    }

	/**
	 * Not implemented.
	 */
	public Iterator<Node> getDepthFirstIterator( boolean directed )
    {
		throw new RuntimeException( "not implemented !" );
    }

	public int getDegree()
    {
	    ArrayList<GraphicEdge> edges = mygraph.connectivity.get( this );
	    
	    if( edges != null )
	    	return edges.size();
	    
	    return 0;
    }

	public Edge getEdge( int i )
    {
		ArrayList<GraphicEdge> edges = mygraph.connectivity.get( this );
		
		if( edges != null && i >= 0 && i < edges.size() )
			return edges.get( i );
		
		return null;
    }

	public Edge getEdgeFrom( String id )
    {
		// TODO
	    return null;
    }

	public Iterator<? extends Edge> getEdgeIterator()
    {
	    ArrayList<GraphicEdge> edges = mygraph.connectivity.get( this );
	    
	    if( edges != null )
	    {
	    	return edges.iterator();
	    }
	    
	    return null;
    }
	
	public Iterator<Edge> iterator()
	{
	    return null;
	}

	public Collection<? extends Edge> getEdgeSet()
    {
		return mygraph.connectivity.get( this );
    }

	public Edge getEdgeToward( String id )
    {
		// TODO
	    return null;
    }

	public Iterator<? extends Edge> getEnteringEdgeIterator()
    {
		return getEdgeIterator();
    }

	public Collection<? extends Edge> getEnteringEdgeSet()
    {
	    return getEdgeSet();
    }

	public Graph getGraph()
    {
	    return mygraph;
    }

	public String getGraphName()
    {
		throw new RuntimeException( "impossible with GraphicGraph" );
    }

	public String getHost()
    {
		throw new RuntimeException( "impossible with GraphicGraph" );
    }

	public int getInDegree()
    {
	    return getDegree();
    }

	public Iterator<? extends Edge> getLeavingEdgeIterator()
    {
		return getEdgeIterator();
    }

	public Collection<? extends Edge> getLeavingEdgeSet()
    {
	    return getEdgeSet();
    }

	public Iterator<Node> getNeighborNodeIterator()
    {
		// TODO
	    return null;
    }

	public int getOutDegree()
    {
	    return getDegree();
    }

	public boolean hasEdgeFrom( String id )
    {
		// TODO
	    return false;
    }

	public boolean hasEdgeToward( String id )
    {
		// TODO
	    return false;
    }

	public boolean isDistributed()
    {
	    return false;
    }

	public void setGraph( Graph graph )
    {
		throw new RuntimeException( "impossible with GraphicGraph" );
    }

	public void setGraphName( String newHost )
    {
		throw new RuntimeException( "impossible with GraphicGraph" );
    }

	public void setHost( String newHost )
    {
		throw new RuntimeException( "impossible with GraphicGraph" );
    }
}