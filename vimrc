sy on
set ai si sw=4 ts=4 expandtab nu noeb ru cul noswapfile relativenumber

autocmd filetype cpp nnoremap <F5> :w <bar> !g++ -Wall -Wno-unused-result -std=c++17   -O2   % -o %:r && ./%:r <CR>

