
Pod::Spec.new do |s|
  s.name         = "RNNetworkState"
  s.version      = "1.0.0"
  s.summary      = "RNNetworkState"
  s.description  = <<-DESC
                  RNNetworkState
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  s.license      = { :type => "MIT", :file => "LICENSE" }
  s.author             = { "author" => "anhtuank7c@hotmail.com" }
  s.platform     = :ios, "8.0"
  s.source       = { :git => "https://github.com/react-native-vietnam/react-native-network-state.git", :tag => "master" }
  s.source_files  = "RNNetworkState/**/*.{h,m}"
  s.requires_arc = true

  s.dependency "React"
  #s.dependency "others"

end
