package org.mule.extension.customConnector.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;

import java.time.*;
import java.time.format.*;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class TimeConvertorOperations {

	@MediaType(value = ANY, strict = false)
	  public String convertTime(String sourceTimeZone, String destinationTimeZone, String timeToConvert) {
	      return convertTimezone(sourceTimeZone, destinationTimeZone, timeToConvert);
	  }

	 

	  @MediaType(value = ANY, strict = false)
	  public String convertTimezone(String sourceTimeZone, String destinationTimeZone, String timeToConvert) {
	      try {
	          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	          LocalDateTime inputTime = LocalDateTime.parse(timeToConvert, formatter);

	 

	          ZoneId sourceZone = ZoneId.of(sourceTimeZone);
	          ZoneId destinationZone = ZoneId.of(destinationTimeZone);

	          ZonedDateTime sourceZonedDateTime = inputTime.atZone(sourceZone);
	          ZonedDateTime destinationZonedDateTime = sourceZonedDateTime.withZoneSameInstant(destinationZone);

	 

	          String convertedTime = destinationZonedDateTime.format(formatter);
	          return convertedTime;
	      } catch (DateTimeParseException e) {
	          return "Invalid time format";
	      } catch (DateTimeException e) {
	          return "Error converting time";
	      }
	  }

	 

	}