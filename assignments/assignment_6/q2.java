import java.util.*;

class MediaPlayer {

    public static class Song {
        protected String title;
        protected String artist;
        protected int duration;

        Song(String title, String artist, int duration) {
            this.title = title;
            this.artist = artist;
            this.duration = duration;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Song)) return false;
            Song s = (Song) o;
            return title.equalsIgnoreCase(s.title) && artist.equalsIgnoreCase(s.artist);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title.toLowerCase(), artist.toLowerCase());
        }

        @Override
        public String toString() {
            return title + " - " + artist + " (" + duration + "s)";
        }
    }

    public static class PlaylistEngine {
        protected LinkedList<Song> queue;
        protected LinkedList<Song> history;
        protected int historyLimit;

        PlaylistEngine() {
            this(10);
        }

        PlaylistEngine(int historyLimit) {
            this.queue = new LinkedList<>();
            this.history = new LinkedList<>();
            this.historyLimit = historyLimit;
        }

        void addToFront(Song song) {
            queue.addFirst(song);
        }

        void add(Song song) {
            queue.addLast(song);
        }

        void addAt(int position, Song song) {
            if (position < 0 || position > queue.size()) {
                throw new IndexOutOfBoundsException("position " + position);
            }
            ListIterator<Song> it = queue.listIterator();
            for (int i = 0; i < position; i++) {
                it.next();
            }
            it.add(song);
        }

        boolean remove(Song song) {
            return queue.remove(song);
        }

        Song playNext() {
            if (queue.isEmpty()) {
                return null;
            }
            Song song = queue.pollFirst();
            history.addFirst(song);
            if (history.size() > historyLimit) {
                history.removeLast();
            }
            return song;
        }

        ArrayList<Song> recentHistory() {
            return new ArrayList<>(history);
        }

        int removeDuplicates() {
            HashSet<Song> seen = new HashSet<>();
            int removed = 0;
            ListIterator<Song> it = queue.listIterator();
            while (it.hasNext()) {
                Song s = it.next();
                if (!seen.add(s)) {
                    it.remove();
                    removed++;
                }
            }
            return removed;
        }

        void traverseForward() {
            ListIterator<Song> it = queue.listIterator();
            while (it.hasNext()) {
                System.out.println("  -> " + it.next());
            }
        }

        void traverseBackward() {
            ListIterator<Song> it = queue.listIterator(queue.size());
            while (it.hasPrevious()) {
                System.out.println("  <- " + it.previous());
            }
        }

        void printQueue() {
            System.out.println(queue);
        }
    }

    public static void main(String[] args) {
        PlaylistEngine player = new PlaylistEngine(3);

        player.add(new Song("Clocks", "Coldplay", 320));
        player.add(new Song("Numb", "Linkin Park", 185));
        player.add(new Song("Clocks", "Coldplay", 320));
        player.add(new Song("Viva la Vida", "Coldplay", 242));
        player.addToFront(new Song("Bohemian Rhapsody", "Queen", 355));
        player.addAt(2, new Song("Yellow", "Coldplay", 267));

        player.printQueue();

        System.out.println("Forward:");
        player.traverseForward();
        System.out.println("Backward:");
        player.traverseBackward();

        System.out.println("Removed duplicates: " + player.removeDuplicates());
        player.printQueue();

        System.out.println("Played: " + player.playNext());
        System.out.println("Played: " + player.playNext());
        System.out.println("Played: " + player.playNext());
        System.out.println("Played: " + player.playNext());
        System.out.println("Recent history: " + player.recentHistory());
    }
}