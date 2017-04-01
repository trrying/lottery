package com.owm.lottery.model.apiplus;

/**
 * 点 所在的位置 枚举
 * Created by ouweiming on 2017/3/31.
 */

public enum PointLocationEnum {

    Code1{
        @Override
        public int getId() {
            return 1;
        }

        @Override
        public String getName() {
            return "第一个号码";
        }
    },
    Code2{
        @Override
        public int getId() {
            return 2;
        }

        @Override
        public String getName() {
            return "第二个号码";
        }
    },
    Code3{
        @Override
        public int getId() {
            return 3;
        }

        @Override
        public String getName() {
            return "第三个号码";
        }
    },
    Code4{
        @Override
        public int getId() {
            return 4;
        }

        @Override
        public String getName() {
            return "第四个号码";
        }
    },
    Code5{
        @Override
        public int getId() {
            return 5;
        }

        @Override
        public String getName() {
            return "第五个号码";
        }
    },
    Code6{
        @Override
        public int getId() {
            return 6;
        }

        @Override
        public String getName() {
            return "第六个号码";
        }
    },
    Code7{
        @Override
        public int getId() {
            return 7;
        }

        @Override
        public String getName() {
            return "第七个号码";
        }
    },
    Sum{
        @Override
        public int getId() {
            return 8;
        }

        @Override
        public String getName() {
            return "合数";
        }
    },
    Sum1{
        @Override
        public int getId() {
            return 9;
        }

        @Override
        public String getName() {
            return "合数十位数";
        }
    },
    Sum2{
        @Override
        public int getId() {
            return 10;
        }

        @Override
        public String getName() {
            return "合数个位数";
        }
    };

    public abstract int getId();
    public abstract String getName();

}
