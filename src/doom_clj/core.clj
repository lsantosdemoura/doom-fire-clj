(ns doom-clj.core
  (:use seesaw.core)
  (:use overtone.at-at))

(defn create-fire-data-structure [fire-width fire-height]
  (let [number-of-pixels (* fire-width fire-height)
        no-fire-source (into [] (replicate (- number-of-pixels fire-width) 0))]
        (concat no-fire-source (replicate fire-width 36))))

(defn display [f content]
  (config! f :content content)
  content)

(defn create-window []
  ((frame :title "Doom fire")))

(defn)

(defn render-fire [debug fire-width fire-height fire-pixels-array row-position fire-colors-palette]
  ; (Thread/sleep 50)
  (if (= row-position fire-height)
    (recur debug fire-width fire-height fire-pixels-array 0))
  (->>
    (range fire-width)
    (map (fn [column] (+ (* fire-width row-position) column)))
    (map (get fire-colors-palette))
    (map write-label))

  fire-pixels-array
  (recur debug fire-width fire-height fire-pixels-array (+ row-position 1)))

(defn write-label [lbl fire-colors-palette position]
  (config! lbl :background (:r (get fire-colors-palette position))
                           (:g (get fire-colors-palette position))
                           (:b (get fire-colors-palette position))))

(defn start []
  (def fire-colors-palette [{:r 7, :g 7, :b 7},
    {:r 31, :g 7, :b 7},
    {:r 47, :g 15, :b 7},
    {:r 71, :g 15, :b 7},
    {:r 87, :g 23, :b 7},
    {:r 103, :g 31, :b 7},
    {:r 119, :g 31, :b 7},
    {:r 143, :g 39, :b 7},
    {:r 159, :g 47, :b 7},
    {:r 175, :g 63, :b 7},
    {:r 191, :g 71, :b 7},
    {:r 199, :g 71, :b 7},
    {:r 223, :g 79, :b 7},
    {:r 223, :g 87, :b 7},
    {:r 223, :g 87, :b 7},
    {:r 215, :g 95, :b 7},
    {:r 215, :g 95, :b 7},
    {:r 215, :g 103, :b 15},
    {:r 207, :g 111, :b 15},
    {:r 207, :g 119, :b 15},
    {:r 207, :g 127, :b 15},
    {:r 207, :g 135, :b 23},
    {:r 199, :g 135, :b 23},
    {:r 199, :g 143, :b 23},
    {:r 199, :g 151, :b 31},
    {:r 191, :g 159, :b 31},
    {:r 191, :g 159, :b 31},
    {:r 191, :g 167, :b 39},
    {:r 191, :g 167, :b 39},
    {:r 191, :g 175, :b 47},
    {:r 183, :g 175, :b 47},
    {:r 183, :g 183, :b 47},
    {:r 183, :g 183, :b 55},
    {:r 207, :g 207, :b 111},
    {:r 223, :g 223, :b 159},
    {:r 239, :g 239, :b 199},
    {:r 255, :g 255, :b 255}])

  (def fire-width 40)
  (def fire-height 40)
  (def fire-pixels-array (create-fire-data-structure fire-width fire-height))
  (render-fire false fire-width fire-height fire-pixels-array))
  ; (create-fire-source fire-pixels-array fire-width fire-height))








; (defn create-fire-source [fire-pixels-array fire-width fire-height]
;   (let [overflow-pixel-index (* fire-width fire-height)
;         pixel-indexes (->>
;                         (range 0 fire-width)
;                         (map (fn [column] (+ (- overflow-pixel-index fire-width) column))))]
;         (map (fn [pixel-index] (assoc fire-pixels-array pixel-index 36)) pixel-indexes)))



(defn -main [& args]
  (start))
