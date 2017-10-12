(ns leiningen.new.keechma
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files sanitize-ns]]
            [leiningen.core.main :as main]))

(def render (renderer "keechma"))

(defn keechma
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)
              :ns-name   (sanitize-ns name)}]
    (main/info "Generating fresh 'lein new' keechma project.")
    (->files data
             [".gitignore" (render ".gitignore" data)]
             ["README.md" (render "README.md" data)]
             ["project.clj" (render "project.clj" data)]

             ["resources/public/index.html" (render "resources/public/index.html" data)]

             ["src/clj/{{sanitized}}/core.clj" (render "src/clj/core.clj" data)]

             ["src/cljs/{{sanitized}}/controllers.cljs" (render "src/cljs/controllers.cljs" data)]
             ["src/cljs/{{sanitized}}/core.cljs" (render "src/cljs/core.cljs" data)]
             ["src/cljs/{{sanitized}}/datasources.cljs" (render "src/cljs/datasources.cljs" data)]
             ["src/cljs/{{sanitized}}/edb.cljs" (render "src/cljs/edb.cljs" data)]
             ["src/cljs/{{sanitized}}/subscriptions.cljs" (render "src/cljs/subscriptions.cljs" data)]
             ["src/cljs/{{sanitized}}/ui.cljs" (render "src/cljs/ui.cljs" data)]
             ["src/cljs/{{sanitized}}/controllers/counter.cljs" (render "src/cljs/controllers/counter.cljs" data)]
             ["src/cljs/{{sanitized}}/ui/main.cljs" (render "src/cljs/ui/main.cljs" data)]

             ["test/cljs/{{sanitized}}/core_test.cljs" (render "test/cljs/core_test.cljs" data)]
             ["test/cljs/{{sanitized}}/runner.cljs" (render "test/cljs/runner.cljs" data)])))
