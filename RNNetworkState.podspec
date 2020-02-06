require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name         = "RNNetworkState"
  s.version      = package['version']
  s.summary      = "RNNetworkState"
  s.description  = package['description']
  s.homepage     = "https://github.com/react-native-vietnam/react-native-network-state#readme"
  s.license      = "MIT"
  s.license      = { :type => "MIT", :file => "LICENSE" }
  s.author       = { "author" => package['author']['name'] }
  s.platform     = :ios, "9.0"
  s.source       = { :git => "https://github.com/react-native-vietnam/react-native-network-state.git", :tag => package['version']}
  s.source_files = "ios/**/*.{h,m}"
  s.framework    = 'SystemConfiguration'

  s.dependency 'React'

end
