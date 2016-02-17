/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.wicket.kendo.ui.widget.treeview;

import java.util.List;

import org.apache.wicket.model.LoadableDetachableModel;

import com.googlecode.wicket.kendo.ui.scheduler.Scheduler;
import com.googlecode.wicket.kendo.ui.scheduler.SchedulerEvent;

/**
 * Model of {@link SchedulerEvent}{@code s} for the {@link Scheduler}<br/>
 * The inheriting class should be able to {@link #load()} events depending on {@link #getStart()} and {@link #getEnd()} dates.
 * 
 * Nodes are any type of object
 * 
 * @see TreeViewNodeFactory
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
public abstract class TreeViewModel<T> extends LoadableDetachableModel<List<T>>
{
	private static final long serialVersionUID = 1L;

	/** root node */
	private String node = null;

	/**
	 * Constructor
	 */
	public TreeViewModel()
	{
		// noop
	}

	@Override
	protected final List<T> load()
	{
		return this.load(this.node);
	}

	protected abstract List<T> load(String node);

	/**
	 * Sets the start date.
	 *
	 * @param date the start date
	 */
	public void setNode(String node)
	{
		this.node = node;
	}
}
