package com.stt;

import java.io.RandomAccessFile;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/11/1.
 */
public class M4aParse {
    private static final int CMP4TAGATOM_ERROR = 0; // 初始化值
    private static final int CMP4TAGATOM_ALBUM = 1; // 专辑
    private static final int CMP4TAGATOM_ARTIST = 2; // 艺术家
    private static final int CMP4TAGATOM_NAME = 3; // 名称
    private static final int CMP4TAGATOM_DATE = 4; // 日期
    private static final int CMP4TAGATOM_GENRE = 5; // 流派
    private static final int CMP4TAGATOM_COVER = 6; // 封面

    private static boolean isInArray(Object[] arr, Object target) {
        for (int i = 0; i < arr.length; i++) {
            if (target.equals(arr[i])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        RandomAccessFile f = new RandomAccessFile("d:/0_20171101025515162.m4a", "r");
        int tagSize, lenSize, currentAtom, lRealBytes;
        byte[] cBuf = new byte[4];
        do {
            tagSize = f.readInt();
            f.readFully(cBuf);
            String tag = new String(cBuf);

            String ignores[] = {"ftyp", "mvhd", "trak"};//不需要解析的标记，跳过整个块
            if (isInArray(ignores, tag)) {
                f.skipBytes(tagSize - 8);
                continue;
            }
            String parents[] = {"moov", "udta", "ilst"};
            if (isInArray(parents, tag)) {//只跳过标记
                continue;
            }
            if ("meta".equals(tag)) {
                f.skipBytes(4);
                continue;
            }
            if ("mdat".equals(tag)) {
                break;
            }

            currentAtom = CMP4TAGATOM_ERROR;
            if (cBuf[0] == (byte) 0xA9) {// 解析专辑、艺术家、名称、年份日期，这些第一个字节值为0xA9
                if (cBuf[1] == 'a' && cBuf[2] == 'l' && cBuf[3] == 'b') {
                    currentAtom = CMP4TAGATOM_ALBUM;
                } else if (cBuf[1] == 'A' && cBuf[2] == 'R' && cBuf[3] == 'T') {
                    currentAtom = CMP4TAGATOM_ARTIST;
                } else if (cBuf[1] == 'n' && cBuf[2] == 'a' && cBuf[3] == 'm') {
                    currentAtom = CMP4TAGATOM_NAME;
                } else if (cBuf[1] == 'd' && cBuf[2] == 'a' && cBuf[3] == 'y') {
                    currentAtom = CMP4TAGATOM_DATE;
                }
            } else if ("gnre".equals(tag)) { // 解析流派
                currentAtom = CMP4TAGATOM_GENRE;
            } else if ("covr".equals(tag)) { // 解析封面图片
                currentAtom = CMP4TAGATOM_COVER;
            }

            if (currentAtom != CMP4TAGATOM_ERROR) {
                lenSize = f.readInt();
                f.readFully(cBuf);

                lRealBytes = lenSize - 16;// 计算实际数据长度
                // 判断长度及标识符是否正确
                if (lenSize + 8 == tagSize
                        && cBuf[0] == 'd' && cBuf[1] == 'a' && cBuf[2] == 't' && cBuf[3] == 'a'
                        && lRealBytes > 0) {
                    f.skipBytes(8);// 当前文件指针位于ver开始处，向后移动8个字节到实际数据处
                    byte[] pRealBuf = new byte[lRealBytes];
                    f.readFully(pRealBuf);// 读取实际数据
                    // 根据ATOM类型解析实际读取的数据
                    HandleRealDataBuf(currentAtom, pRealBuf);
                    continue;// 实际数据读取完成后，文件指针位于下一个ATOM的开始处
                } else {// 格式不对，移动文件指针到下一个ATOM开始的位置，基本不会发生这种情况
                    f.skipBytes(tagSize - 8 - 8);
                }
            } else { // 非解析ATOM，移动文件指针到下一个ATOM的开始位置
                f.skipBytes(tagSize - 8);
            }
        } while (true);
        f.close();
    }

    private static void HandleRealDataBuf(int atomID, byte[] pRealBuf) {
        // 其中流派的实际数据为2个字节，给出的是索引值，需要拿这个索引值在流派类型数组中取出流派字符串
        // 专辑、艺术家、名称、日期的实际数据是UTF-8编码
        // 封面的实际数据就是整个图片数据
        switch (atomID) {
            case CMP4TAGATOM_ALBUM: // 专辑
            case CMP4TAGATOM_ARTIST: // 艺术家
            case CMP4TAGATOM_NAME: // 名称
            case CMP4TAGATOM_DATE: // 日期
                System.out.println(new String(pRealBuf));
                break;
            case CMP4TAGATOM_GENRE: // 流派
                break;
            case CMP4TAGATOM_COVER: // 封面
                break;
            default:
                break;
        }
    }
}
