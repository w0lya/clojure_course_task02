(ns clojure-course-task02.core
  (:gen-class))

;not exactly 'parallel threads for each child dir', but does the required job anyway...
(defn find-files [file-name path]
  (let [file-name-pattern (re-pattern file-name)]
  (->> path
      (clojure.java.io/file)
      (file-seq)
      (pmap #(.getName %))
      (filter (partial re-matches file-name-pattern)))))  

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path)))
	  (shutdown-agents))))
