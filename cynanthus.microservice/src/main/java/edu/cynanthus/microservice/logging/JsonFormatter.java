package edu.cynanthus.microservice.logging;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public final class JsonFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder(500);
        sb.append("{");

        appendJson(sb, "date", "" + appendISO8601(record.getMillis()));

        appendJson(sb, "millis", "" + record.getMillis());

        appendJson(sb, "sequence", "" + record.getSequenceNumber());

        String name = record.getLoggerName();
        if (name != null) {
            appendJson(sb, "name", name);
        }

        appendJson(sb, "level", record.getLevel().toString());

        if (record.getSourceClassName() != null) {
            appendJson(sb, "class", escape(record.getSourceClassName()));
        }

        if (record.getSourceMethodName() != null) {
            appendJson(sb, "method", escape(record.getSourceMethodName()));
        }

        appendJson(sb, "thread", "" + record.getThreadID());

        if (record.getMessage() != null) {
            // Format the message string and its accompanying parameters.
            String message = formatMessage(record);
            appendJson(sb, "message", escape(message));
        }

        // If the message is being localized, output the key, resource
        // bundle name, and params.
        ResourceBundle bundle = record.getResourceBundle();
        try {
            if (bundle != null) {
                bundle.getString(record.getMessage());
                appendJson(sb, "key", escape(record.getMessage()));

                appendJson(sb, "catalog", escape(record.getResourceBundleName()));
            }
        } catch (Exception ex) {
            // The message is not in the catalog. Drop through.
        }

        Object[] parameters = record.getParameters();
        // // Check to see if the parameter was not a messagetext format
        // // or was not null or empty
        if (parameters != null && parameters.length != 0 && !record.getMessage().contains("{")) {
            StringBuilder jsonArray = new StringBuilder();
            for (Object parameter : parameters) {
                try {
                    jsonArray.append(escape(parameter.toString()));
                } catch (Exception ex) {
                    jsonArray.append("???");
                }
            }
            removeLastChar(sb);
            appendJsonArray(sb, "param", jsonArray.toString());
        }

        if (record.getThrown() != null) {
            // Report on the state of the throwable.
            Throwable th = record.getThrown();
            sb.append("\"exception\":{");
            appendJson(sb, "message", escape(th.toString()));
            StackTraceElement[] trace = th.getStackTrace();
            StringBuilder frame_sbu = new StringBuilder();
            for (StackTraceElement frame : trace) {
                frame_sbu.append("{");
                appendJson(frame_sbu, "class", frame.getClassName());
                appendJson(frame_sbu, "method", frame.getMethodName());
                appendJson(frame_sbu, "line", "" + frame.getLineNumber());
                removeLastChar(frame_sbu);
                frame_sbu.append("},");
            }
            removeLastChar(frame_sbu);
            appendJsonArray(sb, "frame", frame_sbu.toString());
            removeLastChar(frame_sbu);
            sb.append("}");
        }

        removeLastChar(sb);
        sb.append("}\n");
        return sb.toString();
    }

    private void removeLastChar(StringBuilder sb) {
        int index = sb.lastIndexOf(",");
        if (index != -1 && index == sb.length() - 1) {
            // Eliminar el último,
            sb.deleteCharAt(index);
        }
    }

    private void appendJson(StringBuilder sb, String tag, String data) {
        sb.append("\"").append(tag).append("\":\"").append(data).append("\",");
    }

    private void appendJsonArray(StringBuilder sb, String tag, String data) {
        sb.append("\"").append(tag).append("\":[").append(data).append("],");
    }

    // Append to the given StringBuilder an escaped version of the
    // given text string where XML special characters have been escaped.
    // For a null string we append "<null>"
    private String escape(String text) {
        StringBuilder sb = new StringBuilder();
        if (text == null) {
            text = "";
        }
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == '<') {
                sb.append("&lt;");
            } else if (ch == '>') {
                sb.append("&gt;");
            } else if (ch == '&') {
                sb.append("&amp;");
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    // Append the time and date in ISO 8601 format
    private String appendISO8601(long millis) {
        StringBuilder sb = new StringBuilder();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(millis);
        sb.append(cal.get(Calendar.YEAR));
        sb.append('-');
        a2(sb, cal.get(Calendar.MONTH) + 1);
        sb.append('-');
        a2(sb, cal.get(Calendar.DAY_OF_MONTH));
        sb.append('T');
        a2(sb, cal.get(Calendar.HOUR_OF_DAY));
        sb.append(':');
        a2(sb, cal.get(Calendar.MINUTE));
        sb.append(':');
        a2(sb, cal.get(Calendar.SECOND));
        return sb.toString();
    }

    // Append a two digit number.
    private void a2(StringBuilder sb, int x) {
        if (x < 10) {
            sb.append('0');
        }
        sb.append(x);
    }

}

