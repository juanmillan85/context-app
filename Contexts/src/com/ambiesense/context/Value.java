package com.ambiesense.context;

import java.io.File;
import java.net.*;
import java.util.Hashtable;

public final class Value extends Hashtable<Long, Object> {

		private static final long serialVersionUID = 1L;

		public static final long STRING            = 1;
		public static final long DOUBLE            = 2;
		public static final long LONG     		   = 3;
		public static final long BOOLEAN           = 4;
		public static final long URL               = 5;
		public static final long FILE              = 6;

		public static final String  DEFAULT_STRING_VALUE  = "";
		public static final Double  DEFAULT_DOUBLE_VALUE  = 0d;
		public static final Boolean DEFAULT_BOOLEAN_VALUE = false;
		public static final Long    DEFAULT_LONG_VALUE    = 0l;
		
		private long timestamp = System.currentTimeMillis();
		private long type = STRING;	
		

	public Value (boolean value, long timestamp){
		this.timestamp = timestamp;
		this.type = BOOLEAN;
		this.put(this.timestamp, value);
	} 
	public Value (long value, long timestamp){
		this.timestamp = timestamp;
		this.type = LONG;
		this.put(this.timestamp, value);
	}
	public Value (String value, long timestamp){
		if (value == null) value = "";
		this.timestamp = timestamp;
		this.type = STRING;
		this.put(this.timestamp, value);
	}
	public Value (double value, long timestamp){
		this.timestamp = timestamp;
		this.type = DOUBLE;
		this.put(this.timestamp, value);
	}
	public Value (URL value, long timestamp){
		//if (value == null) value = "";
		this.timestamp = timestamp;
		this.type = URL;
		this.put(this.timestamp, value);
	}
	public Value (File value, long timestamp){
		//if (value == null) value = "";
		this.timestamp = timestamp;
		this.type = FILE;
		this.put(this.timestamp, value);
	}
	public void updateValue(boolean value){
		if (this.getType() == BOOLEAN){
			this.type = BOOLEAN;
			this.timestamp = System.currentTimeMillis();
			this.put(this.timestamp, value);
		}
	}
	public void updateValue(long value){
		if (this.getType() == LONG){
			this.type = LONG;
			this.timestamp = System.currentTimeMillis();
			this.put(this.timestamp, value);
		}
	}
	public void updateValue(String value){
		if (this.getType() == STRING){
			if (value == null) value = "";
			this.type = STRING;
			this.timestamp = System.currentTimeMillis();
			this.put(this.timestamp, value);
		}
	}
	public void updateValue(double value){
		if (this.getType() == DOUBLE){
			this.type = DOUBLE;
			this.timestamp = System.currentTimeMillis();
			this.put(this.timestamp, value);
		}
	}
	public void updateValue(URL value){
		if (this.getType() == URL){
			this.type = URL;
			this.timestamp = System.currentTimeMillis();
			this.put(this.timestamp, value);
		}
	}
	public void updateValue(File value){
		if (this.getType() == FILE){
			this.type = FILE;
			this.timestamp = System.currentTimeMillis();
			this.put(this.timestamp, value);
		}
	}

	public void updateValue(boolean value, long timestamp){
		if (this.getType() == BOOLEAN){
			this.type = BOOLEAN;
			this.timestamp = timestamp;
			this.put(this.timestamp, value);
		}
	}
	public void updateValue(long value, long timestamp){
		if (this.getType() == LONG){
			this.type = LONG;
			this.timestamp = timestamp;
			this.put(this.timestamp, value);
		}
	}
	public void updateValue(String value, long timestamp){
		if (this.getType() == STRING){
			if (value == null) value = "";
			this.type = STRING;
			this.timestamp = timestamp;
			this.put(this.timestamp, value);
		}
	}
	public void updateValue(double value, long timestamp){
		if (this.getType() == DOUBLE){
			this.type = DOUBLE;
			this.timestamp = timestamp;
			this.put(this.timestamp, value);
		}
	}
	public void updateValue(URL value, long timestamp){
		if (this.getType() == URL){
			this.type = URL;
			this.timestamp = timestamp;
			this.put(this.timestamp, value);
		}
	}
	public void updateValue(File value, long timestamp){
		if (this.getType() == FILE){
			this.type = FILE;
			this.timestamp = timestamp;
			this.put(this.timestamp, value);
		}
	}


	public Object getValue(){
		return this.get(this.timestamp);
	}

	public long getTimestamp(){
		return this.timestamp;
	}

	public long getType(){
		return this.type;
	}
}