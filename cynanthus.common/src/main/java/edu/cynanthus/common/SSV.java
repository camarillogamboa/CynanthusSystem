package edu.cynanthus.common;

import java.io.Flushable;
import java.util.Iterator;
import java.util.Objects;

/**
 * El tipo Ssv.
 */
public final class SSV {

    /**
     * Instancia un nuevo Ssv.
     */
    private SSV() {
    }

    /**
     * To ssv format string.
     *
     * @param <T>       el parámetro de tipo
     * @param values    el values
     * @param separator el separator
     * @return el string
     */
    public static <T> String toSSVFormat(T[] values, String separator) {
        return toSSVFormat(new ArrayIterator<>(values), separator);
    }

    /**
     * To ssv format string.
     *
     * @param iterable  el iterable
     * @param separator el separator
     * @return el string
     */
    public static String toSSVFormat(Iterable<?> iterable, String separator) {
        return toSSVFormat(iterable.iterator(), separator);
    }

    /**
     * To ssv format string.
     *
     * @param iterator  el iterator
     * @param separator el separator
     * @return el string
     */
    public static String toSSVFormat(Iterator<?> iterator, String separator) {
        Builder builder = new Builder(separator);

        while (iterator.hasNext()) builder.append(iterator.next());

        return builder.toString();
    }

    /**
     * El tipo Builder.
     */
    public static final class Builder implements Appendable, Flushable {

        /**
         * El String builder.
         */
        private final StringBuilder stringBuilder;

        /**
         * El Separator.
         */
        private final String separator;

        /**
         * El First.
         */
        private boolean first;

        /**
         * Instancia un nuevo Builder.
         *
         * @param separator el separator
         */
        public Builder(String separator) {
            this.stringBuilder = new StringBuilder();
            this.separator = Objects.requireNonNull(separator);
            first = true;
        }

        /**
         * Permite obtener separator.
         *
         * @return el separator
         */
        public String getSeparator() {
            return separator;
        }

        /**
         * Flush.
         */
        @Override
        public void flush() {
            stringBuilder.setLength(0);
            first = true;
        }

        /**
         * Append builder.
         *
         * @param obj el obj
         * @return el builder
         */
        public Builder append(Object obj) {
            if (first) first = false;
            else stringBuilder.append(separator);
            stringBuilder.append(obj);
            return this;
        }

        /**
         * Append builder.
         *
         * @param csq el csq
         * @return el builder
         */
        @Override
        public Builder append(CharSequence csq) {
            append((Object) csq);
            return this;
        }

        /**
         * Append builder.
         *
         * @param csq   el csq
         * @param start el start
         * @param end   el end
         * @return el builder
         */
        @Override
        public Builder append(CharSequence csq, int start, int end) {
            append(csq.subSequence(start, end));
            return this;
        }

        /**
         * Append builder.
         *
         * @param c el c
         * @return el builder
         */
        @Override
        public Builder append(char c) {
            append((Character) c);
            return this;
        }

        /**
         * To string string.
         *
         * @return el string
         */
        @Override
        public String toString() {
            return stringBuilder.toString();
        }

    }

}
