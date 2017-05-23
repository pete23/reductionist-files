(ns reductionist-files.core)

; leaks the file reader
(defn read-from [file]
  (let [r (clojure.java.io/reader file)]
    (line-seq r)))

(defn write-to [file lines]
  (with-open [w (clojure.java.io/writer file)]
    (doseq [line lines]
      (.write w line)
      (.write w "\n"))))

(defn transform [from to f]
  (write-to to (map f (read-from from))))

;(transform "test.txt" "count.txt" identity)
